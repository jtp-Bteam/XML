
import java.io.*;
import javax.xml.*;
import javax.xml.parsers.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.validation.*;
class CheckXMLShema {
    private final static String xsdfile="shogi.xsd";
    private final static String xmlfile="shogi1.xml";
    private static Validator getValidator(String xsd) throws Exception {
	final SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	final Schema schema = sf.newSchema(new File(xsd));
	return schema.newValidator();
    }
    private static Document getDocument(InputStream is) throws Exception {
	final DocumentBuilderFactory factory
	    = DocumentBuilderFactory.newInstance();
	final DocumentBuilder builder = factory.newDocumentBuilder();
	return builder.parse(is);
    }
    public static void main(String[] arg) throws Exception {
	final Validator validator = getValidator(xsdfile);
	final Document doc = getDocument(new FileInputStream(xmlfile));
	try {
	    validator.validate(new DOMSource(doc));
	    System.out.println("Document validates fine.");
	} catch (org.xml.sax.SAXException e) {
	    System.out.println("Validation error: " + e.getMessage());
	}
    }
}