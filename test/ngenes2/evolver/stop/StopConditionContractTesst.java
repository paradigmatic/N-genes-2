package ngenes2.evolver.stop;


import ngenes2.population.Population;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public abstract class StopConditionContractTesst {

  private StopCondition stopCondition;
  private StopCondition mockTrue;
  private StopCondition mockFalse;
  private int generation;
  private Population population;

  protected abstract StopCondition getStopCondition();
  protected abstract int getStoppingGeneration();
  protected abstract Population getStoppingPopulation();

  @Before
  public final void whenTestingStopConditionContract() {
    stopCondition = getStopCondition();
    mockTrue = mock(StopCondition.class);
    mockFalse = mock(StopCondition.class);
    when( mockTrue.shouldStop(anyInt(), any(Population.class))).thenReturn(Boolean.TRUE);
    when( mockFalse.shouldStop(anyInt(), any(Population.class))).thenReturn(Boolean.FALSE);
    generation = getStoppingGeneration();
    population = getStoppingPopulation();
  }

  @Test
  public void testAnd() {
    assertTrue( stopCondition.shouldStop(generation, population) );
    final StopCondition and = stopCondition.and(mockTrue);
    assertEquals(true, and.shouldStop(generation,population));
    final StopCondition and2 = stopCondition.and(mockFalse);
    assertEquals(false, and2.shouldStop(generation,population));
  }

  @Test
  public void testOr() {
    assertTrue( stopCondition.shouldStop(generation, population) );
    final StopCondition or = stopCondition.or(mockTrue);
    assertEquals(true, or.shouldStop(generation,population));
    final StopCondition or2 = stopCondition.or(mockFalse);
    assertEquals(true, or2.shouldStop(generation,population));
  }

}