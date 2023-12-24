package Server;

import Network.Server.ObjectServer;
import Network.Server.ServerConnectionHandler;
import Network.Server.ServerPlayer;
import Util.Xml;

import java.util.ArrayList;

public class Server {

    private ArrayList<ServerPlayer> players = new ArrayList<>();
    private RawMapData rawMapData;

//    Creates and object server, sets the ServerConnectionHandler, then runs the ObjectServer
    public Server() throws Exception {
        this.rawMapData = Xml.readMap("assets/maps/samplemap1.xml");
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

    public RawMapData getRawMapData() {
        return rawMapData;
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
    }
}
