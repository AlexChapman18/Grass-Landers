package Network.Server;

import java.util.ArrayList;

public class Server {

    public ArrayList<ServerPlayer> players = new ArrayList<>();

//    Creates and object server, sets the ServerConnectionHandler, then runs the ObjectServer
    public Server() throws Exception {
        ObjectServer os = new ObjectServer(8080, this);
        os.run();
    }

//    Adds player to array, and sends a notification to all other players
    public void addPlayer(ServerPlayer player){
        players.add(player);
    }

    //    Adds player to array, and sends a notification to all other players
    public void removePlayer(ServerPlayer player){
        players.remove(player);
    }

    public ArrayList<ServerPlayer> getPlayers(){
        return players;
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
    }
}
