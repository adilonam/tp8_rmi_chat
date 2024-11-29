package app;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        try {
            // Look up the remote object from the registry
            ChatServerInterface chatServer = (ChatServerInterface) Naming.lookup("rmi://localhost:1099/ChatServer");

            // Interact with the remote object
            System.out.println("Connected to ChatServer.");
            // ...additional client logic...

            String randId  =  String.valueOf(new Random().nextInt(1000)); // Generates a random integer between 0 and 999

            ClientDistantInterface client = new ClientDistantImpl(randId, "Client " + randId);

            UnicastRemoteObject.unexportObject(client, true);

            ClientDistantInterface stubClientDistant =(ClientDistantInterface) UnicastRemoteObject.exportObject(client, 0);

            chatServer.signIn(stubClientDistant);

            System.out.println("Client "+ client.getIdDistant()+ " signed in.");

            // User interaction for sending messages
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Send message to all clients");
                System.out.println("2. Send message to a specific client");
                System.out.println("3. Quit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                String prefix = "<"+ client.getIdDistant() +"> ";

                if (choice == 1) {
                    System.out.print("Enter a message to send to all clients: ");
                    String message = scanner.nextLine();
                    chatServer.sendToAll(client.getIdDistant(),prefix + message);
                } else if (choice == 2) {
                    System.out.print("Enter the ID of the client to send the message to: ");
                    String targetClientId = scanner.nextLine();
                    System.out.print("Enter a message to send to client " + targetClientId + ": ");
                    String message = scanner.nextLine();
                    chatServer.sendToOne(client.getIdDistant(), targetClientId,prefix + message);
                } else if (choice == 3) {
                    chatServer.signOut(stubClientDistant ,  prefix +"signed out.");
                    UnicastRemoteObject.unexportObject(client, true);
                    System.out.println("Client "+ client.getIdDistant()+ " signed out.");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }


   

            

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}