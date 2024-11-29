
package app;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientDistantImpl extends ClientLocal implements ClientDistantInterface {

    public ClientDistantImpl(String id, String name) throws RemoteException {
        super(id, name);
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        // Implementation to receive and display a message
        System.out.println("Message received: " + message);
    }

    @Override
    public String getNameDistant() throws RemoteException {
        return this.getName();
    }

    @Override
    public String getIdDistant() throws RemoteException {
        return this.getId();
    }
}