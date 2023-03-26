import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DomParserSchema {
    public static void main(String[] args) {
        try {
            // Create a DocumentBuilderFactory object
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Configure the factory to enable validation against a schema
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("part3.xsd"));
            factory.setSchema(schema);

            // Create a DocumentBuilder object
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Create an ErrorHandler to handle validation errors
            builder.setErrorHandler(new ErrorHandler() {
                public void warning(SAXParseException e) throws SAXException {
                    System.out.println("Warning: " + e.getMessage());
                }

                public void error(SAXParseException e) throws SAXException {
                    System.out.println("Error: " + e.getMessage());
                }

                public void fatalError(SAXParseException e) throws SAXException {
                    System.out.println("Fatal error: " + e.getMessage());
                }
            });

            // Parse the XML document
            Document doc = builder.parse(new File("modules.xml"));

            // Process the document
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
