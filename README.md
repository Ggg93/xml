The repository contains classes and methods to work with XML documents in Java for educational purposes.
It contains:
- dev.gl.xml.dom.EmployeeDOMParser // DOM (JAXP)
- dev.gl.xml.dom.XPathTest // some use cases of XPath in DOM
- dev.gl.xml.sax.EmployeeSAXParser // SAX (JAXP)
- dev.gl.xml.stax.EmployeeStAXParser // StAX (JAXP)
- dev.gl.xml.jaxb.JAXBHandler // JAXB

JAXB contains marshalling and unmarshalling operations.

Dependencies:

JAXB:

- API: jakarta.xml.bind-api 4.0.2
- Implementation: org.glassfish.jaxb.jaxb-runtime 4.0.5

Logging: java.util.logging based on logging.properties

Tests: JUnit5

Hope it helps to familiriaze yourself with JAXP/JAXB simplest operations if needed :)
