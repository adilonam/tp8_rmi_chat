package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MainServer {
    public static void main(String[] args) {
        try {
            // Create and export the registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Create an instance of ChatServerImpl
            ChatServerImpl chatServer = new ChatServerImpl();

            // Bind the remote object's stub in the registry
            Naming.rebind("rmi://localhost:1099/ChatServer", chatServer);

            System.out.println("ChatServer is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
