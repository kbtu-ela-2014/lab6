/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */

import java.rmi.*;
public interface Calculator extends Remote {
    public double sum(double a, double b)throws RemoteException;
    public double sub(double a, double b)throws RemoteException;
    public double mul(double a, double b)throws RemoteException;
    public double div(double a, double b)throws RemoteException;
    public double pow(double a, double b)throws RemoteException;
    public double sin(double a) throws RemoteException;
    public double cos(double a) throws RemoteException;
    public double factor(double a) throws RemoteException;
        
    
} 
