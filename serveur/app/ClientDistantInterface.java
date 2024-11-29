package app;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ClientDistantInterface extends Remote {
    public void receiveMessage(String message) throws RemoteException; // recevoir et afficher un message
    public String getNameDistant() throws RemoteException;
    public String getIdDistant() throws RemoteException;
    } 