import Game.GrassLanders;
import Util.Xml;
import org.w3c.dom.Document;

public class Main {
    public static void main(String[] args) {
        GrassLanders grassLanders = new GrassLanders();
        grassLanders.start();

        // Xml.readMap("assets/map_data/samplemap1.xml");




        System.out.println("Finished");
    }
}