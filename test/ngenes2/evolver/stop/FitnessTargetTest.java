package ngenes2.evolver.stop;

import java.util.Collections;
import ngenes2.individual.Individual;
import ngenes2.population.BasicPopulation;
import ngenes2.population.Population;
import ngenes2.util.Properties;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class FitnessTargetTest extends StopConditionContractTesst {

    private final double bestFitness = 1.0;
    private final Properties props = new Properties().put("fitness_target", bestFitness);
    private  Population pop;
    private StopCondition stop;

    @Before
    public void setup() {
        stop = getStopCondition();
        pop = makePopulation();
    }

    private void addMockIndividual( double fitness, Population pop ) {
        Individual ind = mock(Individual.class);
        when( ind.fitness() ).thenReturn( fitness );
        pop.addToNextGeneration( ind );
        pop.nextGeneration();
    }

    private Population makePopulation() {
      return new BasicPopulation( Collections.EMPTY_LIST );
    }


    @Test
    public void testShouldStop() {
        addMockIndividual(0.5*bestFitness,pop);
        assertEquals( true, stop.shouldStop(0, pop) );
    }

    @Test
    public void testShouldNotStop() {
        addMockIndividual(2*bestFitness,pop);
        assertEquals( false, stop.shouldStop(0, pop) );
    }

  @Override
  protected StopCondition getStopCondition() {
    return new FitnessTarget( props );
  }

  @Override
  protected int getStoppingGeneration() {
    return 0;
  }

  @Override
  protected Population getStoppingPopulation() {
    Population popul = makePopulation();
    addMockIndividual(0.5*bestFitness,popul);
    return popul;
  }

}