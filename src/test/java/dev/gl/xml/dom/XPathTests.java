package dev.gl.xml.dom;

import dev.gl.xml.utils.ExamplesStorage;
import java.io.File;
import java.util.List;
import javax.xml.xpath.XPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

/**
 *
 * @author gl
 */
public class XPathTests {

    private static EmployeeXPathQueries instance;
    private static Document document;
    private static XPath xpath;

    @BeforeAll
    static void init() {
        File file = ExamplesStorage.getFile(ExamplesStorage.TEST_EMPLOYEE_XML_PATH);

        instance = new EmployeeXPathQueries(file);
        instance.createDocumentAndXPath();
        document = instance.getDocument();
        xpath = instance.getXpath();
    }

    @Test
    void documentExists() {
        assumeTrue(document != null, "Document should exist");
    }

    @Test
    void xpathExists() {
        assumeTrue(xpath != null, "XPath should exist");
    }

    @Test
    void checkSurnamesSearch() {
        List<String> surnames = instance.getAllPersonsSecondNames(xpath, document);
        assertAll("Check surnames collection completeness and values",
                () -> assertEquals(1, surnames.size()),
                () -> assertEquals("Adams", surnames.get(0)));
    }
    
    @Test
    void checkEmailsSearch() {
        List<String> emails = instance.getAllEmails(xpath, document);
        assertAll("Check emails collection completeness and values",
                () -> assertEquals(1, emails.size()),
                () -> assertEquals("o_adams@my_test_company.qazqaz", emails.get(0)));
    }
}
