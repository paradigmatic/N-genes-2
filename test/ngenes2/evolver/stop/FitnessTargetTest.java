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

public class FitnessTargetTest {

    private final double bestFitness = 1.0;
    private final Properties props = new Properties().put("fitness_target", bestFitness);
    private  Population pop;
    private StopCondition stop;

    @Before
    public void setup() {
        stop = new FitnessTarget( props );
        pop = new BasicPopulation( Collections.EMPTY_LIST );
    }

    private void addMockIndividual( double fitness ) {
        Individual ind = mock(Individual.class);
        when( ind.fitness() ).thenReturn( fitness );
        pop.addToNextGeneration( ind );
        pop.nextGeneration();
    }

    @Test
    public void testShouldStop() {
        addMockIndividual(0.5*bestFitness);
        assertEquals( true, stop.shouldStop(0, pop) );
    }

    @Test
    public void testShouldNotStop() {
        addMockIndividual(2*bestFitness);
        assertEquals( false, stop.shouldStop(0, pop) );
    }

}