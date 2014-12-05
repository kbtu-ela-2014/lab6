import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcServerImpl extends UnicastRemoteObject
        implements CalcServerIntf {

    public CalcServerImpl() throws RemoteException {
    }

    public double add(double a1, double a2) throws RemoteException {
        return a1 + a2;
    }

    @Override
    public double minus(double a, double b) throws RemoteException {
        return a - b;
    }

    @Override
    public double mult(double m1, double m2) throws RemoteException {
        return m1 * m2;
    }

    @Override
    public double div(double d1, double d2) throws RemoteException {
        return d1 / d2;
    }

    @Override
    public double sqrt(double s) throws RemoteException {
        return Math.sqrt(s);
    }

    @Override
    public double sin(double s1) throws RemoteException {
        return Math.sin(s1);
    }

    @Override
    public double cos(double c1) throws RemoteException {
        return Math.cos(c1);
    }

    @Override
      public double tan(double t1) throws RemoteException {
        return Math.tan(t1);
    }

    @Override
    public double exp(double e) throws RemoteException {
        return Math.exp(e);
    }

    @Override
    public double xy(double x, double y) throws RemoteException {
        return Math.pow(x,y);
    }

    @Override
    public double over(double o) throws RemoteException {
        return 1 / o;
    }

    @Override
    public double square(double sq) throws RemoteException {
        return sq*sq;
    }

    @Override
    public double cube(double q) throws RemoteException {
        return Math.pow(q, 3);
    }

    @Override
    public double two(double tp) throws RemoteException {
        int ans = 1;

        for (int i = 1; i <= tp; i++) {
            ans *= 2;
        }

        return ans;
    }

    @Override
    public double fact(double n) throws RemoteException {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }


}