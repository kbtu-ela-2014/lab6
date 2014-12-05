import java.rmi.Naming;

public class CalcServer {
    public static void main(String args[]) {
        try {
            CalcServerImpl calcServerImpl = new CalcServerImpl();
            Naming.rebind("CalcServer", calcServerImpl);
        }
        catch(Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}