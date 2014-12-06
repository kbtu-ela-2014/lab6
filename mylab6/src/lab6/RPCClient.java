package lab6;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
    
public class RPCClient {
    
  private Connection connection;
  private Channel channel;
  private String requestQueueName = "rpc_queue";
  private String replyQueueName;
  private QueueingConsumer consumer;
    
  public RPCClient() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try {
		connection = factory.newConnection();
		channel = connection.createChannel();

	    replyQueueName = channel.queueDeclare().getQueue(); 
	    consumer = new QueueingConsumer(channel);
	    channel.basicConsume(replyQueueName, true, consumer);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }
  
  public String call(String message) throws Exception {     
    String response = null;
    String corrId = UUID.randomUUID().toString();
    
    BasicProperties props = new BasicProperties
                                .Builder()
                                .correlationId(corrId)
                                .replyTo(replyQueueName)
                                .build();
    
    channel.basicPublish("", requestQueueName, props, message.getBytes());
    
    while (true) {
      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
      if (delivery.getProperties().getCorrelationId().equals(corrId)) {
        response = new String(delivery.getBody(),"UTF-8");
        break;
      }
    }

    return response; 
  }
    
  public void close() throws Exception {
    connection.close();
  }
  
  public static String run(String message, int calcOperation, int currentCalc) {
    
	  RPCClient actionRPC = null;
	  
    try {
      actionRPC = new RPCClient(); 
      System.out.println(" [x] Requesting");   
      String response = actionRPC.call(message + " " + Integer.toString(calcOperation) + " " + Integer.toString(currentCalc));
      return response;
    }
    catch  (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (actionRPC!= null) {
        try {
          actionRPC.close();
        }
        catch (Exception ignore) {}
      }
    }
	return "exception";
  }
}
