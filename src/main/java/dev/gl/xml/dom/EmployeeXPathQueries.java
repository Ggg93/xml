package dev.gl.xml.dom;

import dev.gl.xml.utils.ExamplesStorage;
import dev.gl.xml.utils.Logging;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author gl
 */
public class EmployeeXPathQueries {

    private static final Logger LOGGER = Logging.getLocalLogger(EmployeeXPathQueries.class);
    private final File file;
    public Document document;
    public XPath xpath;

    public EmployeeXPathQueries(File file) {
        this.file = file;
    }

    public static void main(String[] args) {

        File file = ExamplesStorage.getFile(ExamplesStorage.EMPLOYEE_XML_PATH);

        EmployeeXPathQueries instance = new EmployeeXPathQueries(file);
        instance.createXPath();
        
        // get surnames
        List<String> surnames = instance.getAllPersonsSecondNames(instance.xpath, instance.document);
        String collectionInOneLine = surnames.stream().collect(Collectors.joining(", "));
        LOGGER.log(Level.INFO, "surnames: {0}", collectionInOneLine);
        
        // get emails
        List<String> emails = instance.getAllEmails(instance.xpath, instance.document);
        collectionInOneLine = emails.stream().collect(Collectors.joining(", "));
        LOGGER.log(Level.INFO, "emails: {0}", collectionInOneLine);
    }

    public void createXPath() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(file);
            document.getDocumentElement().normalize();

            xpath = XPathFactory.newInstance().newXPath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllPersonsSecondNames(XPath xpath, Document document) {
        String expression = "/Person/SecondName";
        List<String> surnames = new ArrayList<>();

        try {
            NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                surnames.add(node.getTextContent());
            }
        } catch (XPathExpressionException | DOMException e) {
            throw new RuntimeException(e);
        }

        return surnames;
    }

    public List<String> getAllEmails(XPath xpath, Document document) {
        String expression = "/Person/Contacts/Contact[@Type=" + "\"" + "Email" + "\"" + "]/Value";
        List<String> emails = new ArrayList<>();

        try {
            NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                emails.add(node.getTextContent());
            }
        } catch (XPathExpressionException | DOMException e) {
            throw new RuntimeException(e);
        }
        
        return emails;
    }
}
