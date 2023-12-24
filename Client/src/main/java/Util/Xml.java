package Util;

import jade.RawMapData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Xml {

    public static RawMapData readMap(String path) {
        Document doc = readFile(path);

        // Get map data
        NodeList mapNodeList = getElementsByTagName(doc, "map");
        Node mapNode = mapNodeList.item(0);
        Element mapElement = (Element) mapNode;
        String mapName = getMapName(path);
        int width = Integer.parseInt(mapElement.getAttribute("width"));
        int height = Integer.parseInt(mapElement.getAttribute("height"));

        // Get tileset data
        NodeList tilesetNodeList = getElementsByTagName(doc, "tileset");
        Node tilesetNode = tilesetNodeList.item(0);
        Element tilesetElement = (Element) tilesetNode;
        String tilesetSource = tilesetElement.getAttribute("source");

        // Get layer data
        NodeList layerNodeList = getElementsByTagName(doc, "layer");
        int numLayers = layerNodeList.getLength();
        RawMapData rawMapData = new RawMapData(mapName, width, height, tilesetSource, numLayers);

        // Get data from each layer
        for (int layerIndex = 0; layerIndex < layerNodeList.getLength(); layerIndex++) {
            Node currentLayerNode = layerNodeList.item(layerIndex);
            Element currentLayerElement = (Element) currentLayerNode;
            int layerID = Integer.parseInt(currentLayerElement.getAttribute("id"));
            Node layerDataNode = currentLayerElement.getElementsByTagName("data").item(0);
            String csv = layerDataNode.getTextContent()
                    .replace("\n", "")
                    .replace("\r", "")
                    .replace(" ", "");
            int[] mapData = csvStringToIntArray(csv);
            System.out.println(mapData);
            rawMapData.addLayer(layerID, mapData);
        }

        return rawMapData;
    }

    private static String getMapName(String path) {
        int lastSeparatorIndex = path.lastIndexOf('/');
        int lastDotIndex = path.lastIndexOf('.');
        return path.substring(lastSeparatorIndex + 1, lastDotIndex);
    }

    private static Document readFile(String path) {
        try {
            File file = new File(path);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static NodeList getElementsByTagName(Document doc, String tagName){
        return doc.getElementsByTagName(tagName);
    }

    private static int[] csvStringToIntArray(String csvRow) {
        String[] stringValues = csvRow.split(",");
        int[] intArray = new int[stringValues.length];
        for (int i = 0; i < stringValues.length; i++) {
            intArray[i] = Integer.parseInt(stringValues[i]);
        }
        return intArray;
    }



}
