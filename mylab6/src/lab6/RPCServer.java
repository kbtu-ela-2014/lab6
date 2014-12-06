package lab6;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;

import java.math.BigInteger;
import java.util.StringTokenizer;
  
public class RPCServer {
  
  private static final String RPC_QUEUE_NAME = "rpc_queue";
  

  private static String calc(String n){
	  StringTokenizer tokenizer = new StringTokenizer(n);
	  int number = Integer.parseInt(tokenizer.nextToken());
	  int calcOperation = Integer.parseInt(tokenizer.nextToken());
	  int currentCalc = Integer.parseInt(tokenizer.nextToken());
	  
	  if (calcOperation == 1)
      {
          int calculate = currentCalc  + number;
         return Integer.toString(calculate);
      }
      else if (calcOperation == 2)
      {
          int calculate = currentCalc  - number;
          return Integer.toString(calculate);
      }
	return n;
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
      System.out.println(1);
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
          System.out.println(message);
          response = calc(message);
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