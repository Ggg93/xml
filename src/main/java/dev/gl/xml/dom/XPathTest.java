package dev.gl.xml.dom;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author gl
 */
public class XPathTest {

    public static void main(String[] args) {
        File file = new File(".\\src\\main\\resources\\dev\\gl\\xml\\employee\\employee.xml");
        if (!file.exists()) {
            System.out.println("File does not exist");
            return;
        }

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);
            XPath xpath = XPathFactory.newInstance().newXPath();
            printAllPersonsSecondNames(xpath, doc); // get all surnames
            printAllEmails(xpath, doc); // get all emails
        } catch (Exception e) {
            System.out.println("Exception occured: "
                    + e.getClass() + ": " + e.getLocalizedMessage());
            e.printStackTrace(System.out);
        }

    }

    private static void printAllPersonsSecondNames(XPath xpath, Document document) throws XPathExpressionException {
        String expression = "/Person/SecondName";
        NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println("NodeName = " + node.getNodeName() + ", value = " + node.getTextContent());
        }
    }

    private static void printAllEmails(XPath xpath, Document document) throws XPathExpressionException {
        String expression = "/Person/Contacts/Contact[@Type=" + "\"" + "Email" + "\"" + "]/Value";
        NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println("NodeName = " + node.getNodeName() + ", value = " + node.getTextContent());
        }
    }
}
