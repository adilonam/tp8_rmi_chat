package app;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ChatServerInterface extends Remote {
    String LOOKUP_NAME="ChatServer";
    public void sendToAll(String SenderName,String message) throws
    RemoteException;
    public void sendToOne(String SenderName,String receiverName, String message)
    throws RemoteException;
    public void signIn(ClientDistantInterface c) throws RemoteException;
    public void signOut(ClientDistantInterface c, String message) throws
    RemoteException;
    }