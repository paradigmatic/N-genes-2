package ngenes2.evolver.stop;

import ngenes2.population.Population;
import ngenes2.util.Properties;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MaxGenerationTest extends StopConditionContractTesst {

  private final int max = 13;
  private final Properties props = new Properties().put("max_generation", max);
  private MaxGeneration condition;
  private Population population;

  @Before
  public void setup() throws Exception {
    condition = (MaxGeneration) getStopCondition();
    population = getStoppingPopulation();

  }

  @Test
  public void testBeforeMax() {
    assertEquals(false, condition.shouldStop(max / 2, null));
  }

  @Test
  public void testAfterMax() {
    assertEquals(true, condition.shouldStop(max, null));
  }

  @Override
  protected StopCondition getStopCondition() {
    return new MaxGeneration(props);
  }

  @Override
  protected int getStoppingGeneration() {
    return max;
  }

  @Override
  protected Population getStoppingPopulation() {
    return mock(Population.class);
  }
}
