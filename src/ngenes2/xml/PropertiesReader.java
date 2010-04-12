package ngenes2.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import ngenes2.util.Properties;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parse properties contained in an XML document. The XML format is:
 * <pre>
&lt;ngenes2&gt;
&lt;properties&gt;
&lt;property tournament_size="3" /&gt;
&lt;property population_size="20" /&gt;
&lt;property chromosome_size="30" /&gt;
&lt;property fitness_target="10e-9" /&gt;
&lt;/properties&gt;"
&lt;/ngenes2&gt;
 * </pre>
 * @author falcone
 */
public class PropertiesReader {

  private final static Logger logger = LoggerFactory.getLogger(XMLParser.class);
  final private Properties props;

  /**
   * Parse an xml document to extract properties. The document is accessed using a reader-
   * @param reader Reader giving access to the xml document
   * @throws ParsingException
   * @throws IOException
   */
  public PropertiesReader(Reader reader) throws ParsingException, IOException {
    logger.info("Parsing XML Properties");
    Builder parser = new Builder();
    Document doc = parser.build(reader, null);
    props = parseProperties(doc);
  }

  private static Properties parseProperties(Document doc) {
    Properties props = new Properties();
    Nodes propNodes = doc.query("//properties/property");
    for (int i = 0; i < propNodes.size(); i++) {
      Element el = (Element) propNodes.get(i);
      Attribute attr = el.getAttribute(0);
      props.put(attr.getLocalName(), attr.getValue());
    }
    return props;
  }

  /**
   * Get the parsed properties.
   * @return Properties
   */
  public Properties properties() {
    return props;
  }

  /**
   * Convenience method to parse properties from a reader.
   * @param reader Reader
   * @return Parsed properties.
   * @throws ParsingException
   * @throws IOException
   */
  public static Properties parse(Reader reader) throws ParsingException, IOException {
    PropertiesReader parser = new PropertiesReader(reader);
    return parser.properties();
  }

  /**
   * Convenience method to parse properties from string containing the XML document.
   * @param input A string containing the xml file
   * @return Parsed properties.
   * @throws ParsingException
   * @throws IOException
   */
  public static Properties fromString(String input) throws ParsingException, IOException {
    return parse(new StringReader(input));
  }

  /**
   * Convenience method to parse properties from an xml file.
   * @param  f An xml file
   * @return Parsed properties.
   * @throws ParsingException
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static Properties fromFile(File f) throws FileNotFoundException, ParsingException, IOException {
    return parse(new FileReader(f));
  }

  /**
   * Convenience method to parse properties from an xml file, using the file name.
   * @param  filename An xml file name
   * @return Parsed properties.
   * @throws ParsingException
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static Properties fromFile(String filename) throws FileNotFoundException, ParsingException, IOException {
    return parse(new FileReader(filename));
  }
}
