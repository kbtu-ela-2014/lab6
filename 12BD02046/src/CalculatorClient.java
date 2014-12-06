
import java.rmi.*; 
import java.io.*; 
public class CalculatorClient {    
    public static void  main(String args[]) { 
        try{ 
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
            Calculator p=(Calculator)Naming.lookup("Cal"); 
        } 
        catch(Exception e) {
            System.out.println("Exception occurred : "+e.getMessage());
        } 
    } 
}