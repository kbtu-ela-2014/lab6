
import java.rmi.*; 
import java.rmi.server.*; 
public class CalculatorServer extends UnicastRemoteObject implements Calculator { 
    public CalculatorServer()throws RemoteException { 
        System.out.println("Server is Instantiated"); 
    } 
    public double sum(double first,double Second) throws RemoteException    { 
        return first+Second; 
    }    
    public double sub(double first,double Second) throws RemoteException    { 
        return first-Second; 
    }    
    public double mul(double first,double Second) throws RemoteException    { 
        return first*Second; 
    }   
    public double pow(double first,double Second) throws RemoteException    { 
        return Math.pow(first,Second); 
    } 
    public double div(double first,double Second) throws RemoteException    { 
        return first/Second; 
    } 
    public double sin(double first) throws RemoteException{
        double result;
        double rads=Math.toRadians(first);
        result=Math.sin(rads);
        return result;
    }
    public double cos(double first) throws RemoteException{
        double result;
        double rads=Math.toRadians(first);
        result=Math.cos(rads);
        return result;
    }
    

  //  @Override
    public double factor(double a) throws RemoteException {
        double fact = 1;
            
                for ( int c = 1 ; c <= a ; c++ ){
                   fact = fact*c;
                   //System.out.println("Factorial of "+num+" is = "+fact);
                }
                return fact;
         }
    
    public static void main(String arg[]) { 
        try{ 
            CalculatorServer p=new CalculatorServer(); 
            Naming.rebind("Cal",p); 
        }
        catch(Exception e) { 
            System.out.println("Exception occurred : "+e.getMessage()); 
        } 
    } 

   // @Override
 /*   public double sin(double a) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    */

   

   
} 