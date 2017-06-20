import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.*;
import javax.xml.parsers.*;
import javax.xml.validation.*;
class TestHandler extends DefaultHandler {
  public TestHandler(){}
  @Override
  public  void error(SAXParseException e) throws SAXException {
    System.out.println(e);
  }
}
class CheckDTD {
  private static SAXParser getSAXParserWDTD() throws Exception {
    final SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    parserFactory.setValidating(true);
    parserFactory.setNamespaceAware(true);
    return parserFactory.newSAXParser();
  }
  public static void main(String[] arg) throws Exception {
    final SAXParser parser = getSAXParserWDTD();
    parser.parse(new FileInputStream("shogi2.xml"),new TestHandler());
  }
}