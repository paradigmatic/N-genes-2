package ngenes2.evolver.stop;

import ngenes2.population.Population;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class AndConditionTest extends StopConditionContractTesst {

  private StopCondition condFalse;
  private StopCondition condTrue;

  @Before
  public void setup() {
    condFalse = mock(StopCondition.class);
    when(condFalse.shouldStop(anyInt(), any(Population.class))).thenReturn(false);
    condTrue = mock(StopCondition.class);
    when(condTrue.shouldStop(anyInt(), any(Population.class))).thenReturn(true);
  }

  @Test
  public void testAnd() {
    assertEquals(false, new AndCondition(condFalse, condFalse).shouldStop(0, null));
    assertEquals(false, new AndCondition(condFalse, condTrue).shouldStop(0, null));
    assertEquals(false, new AndCondition(condTrue, condFalse).shouldStop(0, null));
    assertEquals(true, new AndCondition(condTrue, condTrue).shouldStop(0, null));
  }

  @Override
  protected StopCondition getStopCondition() {
    setup();
    return new AndCondition(condTrue, condTrue);
  }

  @Override
  protected int getStoppingGeneration() {
    return 0;
  }

  @Override
  protected Population getStoppingPopulation() {
    return mock(Population.class);
  }
}
