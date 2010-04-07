package ngenes2.xml;

import java.io.IOException;
import ngenes2.population.Population;
import ngenes2.util.Properties;
import nu.xom.ParsingException;
import org.junit.Test;
import static org.junit.Assert.*;

public class XMLParserTest {

    private final String xml = "<ngenes2>" +
            "<properties>" +
            "<property foo=\"2\" />" +
            "<property bar=\"false\" />" +
            "<property baz=\"0.125\" />" +
            "<property funky=\"shit\" />" +
            "</properties>" +
            "<evolver>" +
            "<with class=\"ngenes2.individual.LinearIndividual$Factory\" />" +
            "<with class=\"ngenes2.examples.MaxOnes$Fitness\" />" +
            "</evolver>" +
            "</ngenes2>";

    @Test
    public void testPropertiesParsing() throws ParsingException, IOException, ClassNotFoundException {
        XMLParser parser = XMLParser.fromString(xml);
        Properties props = parser.properties();
        assertEquals(2, props.getInt("foo"));
        assertEquals(false, props.getBoolean("bar"));
        assertEquals(0.125, props.getDouble("baz"), 1e-18);
        assertEquals("shit", props.getString("funky"));
    }

    @Test
    public void testElvolverParsing() throws ParsingException, IOException, ClassNotFoundException {
        XMLParser parser = XMLParser.fromString(xml);
        Population pop = parser.result();
        assertTrue( pop != null );
    }
}
