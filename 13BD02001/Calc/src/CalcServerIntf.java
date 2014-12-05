import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalcServerIntf extends Remote {
    double add(double a1, double a2) throws RemoteException;
    double minus(double a, double b) throws RemoteException;
    double mult(double m1, double m2) throws RemoteException;
    double div(double d1, double d2) throws RemoteException;
    double sqrt(double s) throws RemoteException;
    double fact(double n) throws RemoteException;
    double xy(double x, double y) throws RemoteException;
    double exp(double e) throws RemoteException;
    double square(double sq) throws RemoteException;
    double cube(double q) throws RemoteException;
    double over(double o) throws RemoteException;
    double two(double tp) throws RemoteException;
    double sin(double s1) throws RemoteException;
    double cos(double c1) throws RemoteException;
    double tan(double t1) throws RemoteException;
}
