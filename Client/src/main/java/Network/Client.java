// package Network;
//
// import Network.Shared.Packet.PacketDefinition.ServerboundChatPacket;
// import Network.Shared.Packet.PacketDefinition.ServerboundLoginPacket;
//
// import java.util.Scanner;
//
// public class Client {
//     private ClientConnectionHandler cch;
//     private int AUTHCODE = 2153;
//     private String username = "Jack";
//
//
//     public Client(String host, int port, String name) {
//         this.username = name;
//         ObjectClient oc = new ObjectClient(host, port, this);
//         this.cch = oc.getCch();
//         oc.run();
//     }
//
//     private void sendLoginPacket() {
//         ServerboundLoginPacket sblp = new ServerboundLoginPacket(AUTHCODE, username);
//         cch.sendPacket(sblp);
//     }
//
//
//     private void sendChat(String message) {
//         ServerboundChatPacket sbcp = new ServerboundChatPacket("Global", message);
//         cch.sendPacket(sbcp);
//     }
//
//     public void connected() {
//         sendLoginPacket();
//
//         while (true) {
//             Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//             String message = myObj.nextLine();  // Read user input
//             sendChat(message);
//         }
//     }
//
//
//     public static void main(String[] args) throws Exception {
//         // Client server = new Client(args[0], Integer.parseInt(args[1]), args[2]);
//         Client server = new Client("localhost", 8080, "Alex");
//     }
// }
