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


public class PropertiesReader {

    private final static Logger logger = LoggerFactory.getLogger(XMLParser.class);
    final private Properties props;

    public PropertiesReader(Reader reader) throws ParsingException, IOException {
        logger.info("Parsing XML string input");
        Builder parser = new Builder();
        Document doc = parser.build(reader, null);
        props = parseProperties(doc);
    }

    private static Properties parseProperties(Document doc) {
        logger.debug("Parsing properties");
        Properties props = new Properties();
        Nodes propNodes = doc.query("//properties/property");
        for (int i = 0; i < propNodes.size(); i++) {
            Element el = (Element) propNodes.get(i);
            Attribute attr = el.getAttribute(0);
            props.parse(attr.getLocalName(), attr.getValue());
        }
        return props;
    }

    public Properties properties() {
        return props;
    }

    public static Properties parse( Reader reader ) throws ParsingException, IOException {
      PropertiesReader parser = new PropertiesReader( reader );
        return parser.properties();
    }

    public static Properties fromString( String input ) throws ParsingException, IOException {
        return parse( new StringReader(input) );
    }

    public static Properties fromFile( File f ) throws FileNotFoundException, ParsingException, IOException {
        return parse( new FileReader(f) );
    }

    public static Properties fromFile( String filename ) throws FileNotFoundException, ParsingException, IOException {
        return parse( new FileReader(filename) );
    }


}
