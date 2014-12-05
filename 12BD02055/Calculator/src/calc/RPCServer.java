package calc;
import java.util.ArrayList;
import java.util.List;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;
  
public class RPCServer {
  
  private static final String RPC_QUEUE_NAME = "rpc_queue";
  
 
  
  private static String plus(String x, String y){
      Integer ans = Integer.parseInt(x)+Integer.parseInt(y);
      return ans.toString();
  }
  
  private static String minus(String x, String y){
      Integer ans = Integer.parseInt(x)-Integer.parseInt(y);
      return ans.toString();
  }
  
  private static String multiply(String x, String y){
      Integer ans = Integer.parseInt(x)*Integer.parseInt(y);
      return ans.toString();
  }
  
  private static String divide(String x, String y){
      Integer ans = Integer.parseInt(x)/Integer.parseInt(y);
      return ans.toString();
  }
  
  private static String factorial(Integer n){
      if(n==0)return "0";
      if(n==1) return "1";
      Integer ans=1;
      
      for(int i=2; i<=n;i++)
          ans*=i;
      
      return ans.toString();
  }
  
  private static String power(String x, String y){
   Integer ans = 1;
   Integer a = Integer.parseInt(x);
   Integer b = Integer.parseInt(y);
   for(int i=1; i<=b; i++){
       ans*=a;
   }
   
   return ans.toString();
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
          
          char c = message.charAt(0);
          StringBuilder sb = new StringBuilder(message);
          sb.deleteCharAt(0);
          String str = sb.toString();
          
          //int n = Integer.parseInt(str);
          if(c=='+'){
              String x="";
              String y="";
              int cnt=0;
              while(str.charAt(cnt)!='|'){
                  x+=str.charAt(cnt);
                  cnt++;
              }
              cnt+=2;
              
              while(cnt<str.length()){
                  y+=str.charAt(cnt);
                  cnt++;
              }
             response = ""+plus(x,y);
          }else if(c=='-'){
              String x="";
              String y="";
              int cnt=0;
              while(str.charAt(cnt)!='|'){
                  x+=str.charAt(cnt);
                  cnt++;
              }
              cnt+=2;
              
              while(cnt<str.length()){
                  y+=str.charAt(cnt);
                  cnt++;
              }
             response = ""+minus(x,y);
          }else if(c=='*'){
              String x="";
              String y="";
              int cnt=0;
              while(str.charAt(cnt)!='|'){
                  x+=str.charAt(cnt);
                  cnt++;
              }
              cnt+=2;
              
              while(cnt<str.length()){
                  y+=str.charAt(cnt);
                  cnt++;
              }
             response = ""+multiply(x,y);
          }else if(c=='/'){
              String x="";
              String y="";
              int cnt=0;
              while(str.charAt(cnt)!='|'){
                  x+=str.charAt(cnt);
                  cnt++;
              }
              cnt+=2;
              
              while(cnt<str.length()){
                  y+=str.charAt(cnt);
                  cnt++;
              }
             response = ""+divide(x,y);
          }else if(c=='!'){
              Integer n = Integer.parseInt(str);
              response = ""+factorial(n);
          }else if(c=='^'){
              String x="";
              String y="";
              int cnt=0;
              while(str.charAt(cnt)!='|'){
                  x+=str.charAt(cnt);
                  cnt++;
              }
              cnt+=2;
              
              while(cnt<str.length()){
                  y+=str.charAt(cnt);
                  cnt++;
              }
             response = ""+power(x,y);
          }
              
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
