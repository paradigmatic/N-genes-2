package ngenes2.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import ngenes2.population.Population;
import ngenes2.util.Properties;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLParser {
    
    private final static Logger logger = LoggerFactory.getLogger(XMLParser.class);

    final private Properties props;
    final private Population population;

    public XMLParser( Reader reader ) throws ParsingException, IOException {
        logger.info("Parsing XML string input");
        Builder parser = new Builder();
        Document doc = parser.build(reader, null);
        props = parseProperties( doc );
        population = parseAndRunEvolver( doc, props );
    }

    private static Properties parseProperties(Document doc) {
        logger.debug("Parsing properties");
        Properties props = new Properties();
        Nodes propNodes = doc.query("//properties/property");
        for( int i=0; i < propNodes.size(); i++ ) {
            Element el = (Element) propNodes.get(i);
            Attribute attr = el.getAttribute(0);
            props.parse( attr.getLocalName(), attr.getValue() );
        }
        return props;
    }

    public Properties properties() {
        return props;
    }

    static public XMLParser fromString(String content) throws ParsingException, IOException {
        return new XMLParser(new StringReader(content));
    }

    private Population parseAndRunEvolver(Document doc, Properties props) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
