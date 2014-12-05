import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class CalcServer{
	
	private static DatagramSocket serverSocket;

	public static void main(String args[]) throws IOException {
		serverSocket = new DatagramSocket(1123);
		byte[] receiveData = new byte[1024];             
		byte[] sendData = new byte[1024]; 
		while(true){                   
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);                   
			serverSocket.receive(receivePacket);      
				                
			Oper opera = Oper.fromBytes(receivePacket.getData());                
            double result = 0;
            
            if (opera.lastOperator.equals("/")) result = opera.lastNumber / opera.numberInDisplay;	
    		if (opera.lastOperator.equals("*")) result = opera.lastNumber * opera.numberInDisplay;
    		if (opera.lastOperator.equals("-")) result = opera.lastNumber - opera.numberInDisplay;
    		if (opera.lastOperator.equals("+")) result = opera.lastNumber + opera.numberInDisplay;
    		if (opera.lastOperator.equals("%")) result = opera.numberInDisplay / 100;
    		if (opera.lastOperator.equals("1/x")) result = 1 / opera.numberInDisplay;
    		if (opera.lastOperator.equals("sqrt")) result = Math.sqrt(opera.numberInDisplay);     
    		
			sendData = Double.toString(result).getBytes();                                    
			serverSocket.send(new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort()));                
		} 
	}
}