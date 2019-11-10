import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            // Read and parse the document
            File inputFile = new File(
                    "D:\\Univer\\Univer\\Anul 3\\Semestrul 1\\SBC\\lab\\Lab04 XML\\official_exchange_rates.xml"
            );
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("\nDocumentul " + inputFile.getName() + ":");
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------");

            // Get Valute nodes
            NodeList nList = doc.getElementsByTagName("Valute");
            // Sort the NodeList in descending order by the given child tag
            Node[] sortedNodes = NodeTools.sortNodes(nList, "Value", false, Float.class);

            // Print nodes
            for (Node nNode : sortedNodes) {
                System.out.println("\nCurrent Element: " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Valute ID: "
                            + eElement.getAttribute("ID"));
                    System.out.println("NumCode: "
                            + eElement
                            .getElementsByTagName("NumCode")
                            .item(0)
                            .getTextContent());
                    System.out.println("CharCode: "
                            + eElement
                            .getElementsByTagName("CharCode")
                            .item(0)
                            .getTextContent());
                    System.out.println("Nominal: "
                            + eElement
                            .getElementsByTagName("Nominal")
                            .item(0)
                            .getTextContent());
                    System.out.println("Name: "
                            + eElement
                            .getElementsByTagName("Name")
                            .item(0)
                            .getTextContent());
                    System.out.println("Value: "
                            + eElement
                            .getElementsByTagName("Value")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
