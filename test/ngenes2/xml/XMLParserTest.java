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
          "<property tournament_size=\"3\" />" +
          "<property population_size=\"20\" />" +
          "<property chromosome_size=\"30\" />" +
          "<property fitness_target=\"10e-9\" />" +
          "</properties>" +
          "<components>" +
          "<with class=\"ngenes2.individual.LinearIndividual$Factory\" />" +
          "<with class=\"ngenes2.individual.generator.bool.RandomBooleanGenerator\" />" +
          "<with class=\"ngenes2.ops.selector.KTournament\" />" +
          "<with class=\"ngenes2.ops.mutator.genes.bool.BooleanFlipper\" />" +
          "<with class=\"ngenes2.ops.mutator.PointMutation\"/>" +
          "<with class=\"ngenes2.ops.crossover.MidBreakCrossover\" />" +
          "<with class=\"ngenes2.evolver.monitor.NopMonitor\" />" +
          "<with class=\"ngenes2.evolver.stop.FitnessTarget\" />" +
          "<with class=\"ngenes2.examples.MaxOnes$Fitness\" />" +
          "</components>" +
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
    assertTrue(pop != null);
    assertEquals(20, pop.size());

  }
}
