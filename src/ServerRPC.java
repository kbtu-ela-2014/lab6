import java.util.StringTokenizer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;
  
public class ServerRPC {
  
  private static final String RPC_QUEUE_NAME = "rpc_queue";
  
  private static double calculate(String op, String ops) {
	  double res=0;
	  StringTokenizer st=new StringTokenizer(ops, " ");
	  System.out.println("OPS "+ops);
	  double var1=0;
	  double var2=0;
	  String a=st.nextToken();
	  String b=st.nextToken();
	  
	 
	  if(!a.equals("none")){
		  var1=Double.parseDouble(a);
	  }
	  if(!b.equals("none")){
		  var2=Double.parseDouble(b);
	  }
	 
	  if(op.equals("+")){
		 res=var1+var2;
	  }
	  
	  if(op.equals("sin")){
		  System.out.println(var1);
		  double rads=Math.toRadians(var1);
		  res=Math.sin(rads);
	  }
	  if(op.equals("cos")){
		  System.out.println(var1);
		  double rads=Math.toRadians(var1);
		  res=Math.cos(rads);
	  }
	  if(op.equals("pow")){
		  res=Math.pow(var1, var2);
	  }
	  
   return res;
  }
    
  public static void main(String[] argv) {
    Connection connection = null;
    Channel channel = null;
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
  
      connection = factory.newConnection();
      channel = connection.createChannel();
      
      channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
  
      channel.basicQos(1);
  
      QueueingConsumer consumer = new QueueingConsumer(channel);
      channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
  
      System.out.println(" [x] Awaiting RPC requests");
  
      while (true) { 
        String response = null;
        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        
        BasicProperties props = delivery.getProperties();
        BasicProperties replyProps = new BasicProperties
                                         .Builder()
                                         .correlationId(props.getCorrelationId())
                                         .build();
        
        try {
          String message = new String(delivery.getBody(),"UTF-8");
          System.out.println("Message "+message);
          StringTokenizer st=new StringTokenizer(message, " ");
          String operation="";
          String a="";
          String b="none";
          int cnt=0;
          
          operation=st.nextToken();
          a=st.nextToken();
         if(st.hasMoreTokens()){
        	 b=st.nextToken();
         }
          response = ""+calculate(operation, a+" "+b);
        }
        catch (Exception e){
          System.out.println(" [.] " + e.toString());
          response = "";
        }
        finally {  
          channel.basicPublish( "", props.getReplyTo(), replyProps, response.getBytes("UTF-8"));
  
          channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
      }
    }
    catch  (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (connection != null) {
        try {
          connection.close();
        }
        catch (Exception ignore) {}
      }
    }      		      
  }
}