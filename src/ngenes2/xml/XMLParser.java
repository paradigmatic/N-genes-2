package ngenes2.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import ngenes2.ClassicInstanciator;
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

    public XMLParser(Reader reader) throws ParsingException, IOException, ClassNotFoundException {
        logger.info("Parsing XML string input");
        Builder parser = new Builder();
        Document doc = parser.build(reader, null);
        props = parseProperties(doc);
        population = parseAndRunEvolver(doc, props);
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

    public Population result() {
        return population;
    }

    @SuppressWarnings("unchecked")
    private static Population parseAndRunEvolver(Document doc, Properties props) throws ClassNotFoundException {
        logger.debug("Parsing components");
        ClassicInstanciator inst = new ClassicInstanciator().with(props);
        Nodes withs = doc.query("//components/with");
        for (int i = 0; i < withs.size(); i++) {
            Element el = (Element) withs.get(i);
            Attribute attr = el.getAttribute("class");
            Class klass = Class.forName(attr.getValue());
            inst.with(klass);
        }
        return inst.run();
    }

    public static XMLParser fromString(String content) throws ParsingException, IOException, ClassNotFoundException {
        return new XMLParser(new StringReader(content));
    }

    public static XMLParser fromFile(String filename) throws FileNotFoundException, ParsingException, IOException, ClassNotFoundException {
        return new XMLParser( new FileReader( filename ) );
    }


}
