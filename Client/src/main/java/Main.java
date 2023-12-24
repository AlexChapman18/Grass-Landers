import Game.GrassLanders;

public class Main {
    public static void main(String[] args) {
        GrassLanders grassLanders = new GrassLanders();
        grassLanders.startGame("localhost", 8080, "Alex");
        // Xml.readMap("assets/map_data/samplemap1.xml");
        // System.out.println("Finished");
    }
}