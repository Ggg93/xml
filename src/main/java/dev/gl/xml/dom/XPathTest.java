package dev.gl.xml.dom;

import dev.gl.xml.utils.ExamplesStorage;
import dev.gl.xml.utils.Logging;
import java.io.File;
import java.util.logging.Logger;
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

    private static final Logger LOGGER = Logging.getLocalLogger(XPathTest.class);

    public static void main(String[] args) {
        
        File file = ExamplesStorage.getFile(ExamplesStorage.EMPLOYEE_XML_PATH);

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);
            XPath xpath = XPathFactory.newInstance().newXPath();
            printAllPersonsSecondNames(xpath, doc); // get all surnames
            printAllEmails(xpath, doc); // get all emails
        } catch (Exception e) {
            LOGGER.severe("Exception occured: "
                    + e.getClass() + ": " + e.getLocalizedMessage());
            e.printStackTrace(System.out);
        }

    }

    private static void printAllPersonsSecondNames(XPath xpath, Document document) throws XPathExpressionException {
        String expression = "/Person/SecondName";
        NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            LOGGER.info("NodeName = " + node.getNodeName() + ", value = " + node.getTextContent());
        }
    }

    private static void printAllEmails(XPath xpath, Document document) throws XPathExpressionException {
        String expression = "/Person/Contacts/Contact[@Type=" + "\"" + "Email" + "\"" + "]/Value";
        NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            LOGGER.info("NodeName = " + node.getNodeName() + ", value = " + node.getTextContent());
        }
    }
}
