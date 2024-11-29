package app;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServerInterface {
    private List<ClientDistantInterface> clients;

    protected ChatServerImpl() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override
    public void sendToAll(String senderName, String message) throws RemoteException {
        for (ClientDistantInterface client : clients) {
            client.receiveMessage(message);
        }
    }

    @Override
    public void sendToOne(String senderName, String receiverName, String message) throws RemoteException {
        for (ClientDistantInterface client : clients) {
            if (client.getIdDistant().equals(receiverName)) {
                client.receiveMessage(message);
                break;
            }
        }
    }

    @Override
    public void signIn(ClientDistantInterface c) throws RemoteException {
        clients.add(c);
        System.out.println("Client " + c.getNameDistant() + " signed in");
    }

    @Override
    public void signOut(ClientDistantInterface c, String message) throws RemoteException {
        clients.remove(c);
        sendToAll("Server", message);
    }
}