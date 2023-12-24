package Game;

import Network.Server.ObjectServer;
import Network.Server.Player;
import Util.Xml;
import jade.RawMapData;

import java.util.ArrayList;

public class Server {

    private ArrayList<Player> players = new ArrayList<>();
    private RawMapData rawMapData;

//    Creates and object server, sets the ServerConnectionHandler, then runs the ObjectServer
    public Server() throws Exception {
        this.rawMapData = Xml.readMap("assets/maps/samplemap1.xml");
        ObjectServer os = new ObjectServer(8080, this);
        os.run();
    }

//    Adds player to array, and sends a notification to all other players
    public void addPlayer(Player player){
        players.add(player);
    }

    //    Adds player to array, and sends a notification to all other players
    public void removePlayer(Player player){
        players.remove(player);
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public RawMapData getRawMapData() {
        return rawMapData;
    }
}
