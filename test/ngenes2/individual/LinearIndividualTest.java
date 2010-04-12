package ngenes2.individual;

import java.util.ArrayList;
import java.util.List;
import ngenes2.fitness.Fitness;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinearIndividualTest {

    private Fitness<Integer> fixedFitness = new Fitness<Integer>() {

        public double compute(List<Integer> chromosome) {
            return 12.0;
        }
    };
    private List<Integer> chromosome;
    private LinearIndividual<Integer> individual;


    @Before
    public void makeIndividual() {
        chromosome = new ArrayList<Integer>();
        chromosome.add(0);
        chromosome.add(1);
        chromosome.add(2);
        individual = new LinearIndividual<Integer>(fixedFitness, chromosome);
    }


    @Test
    public void testFitness() {
        assertEquals(12.0, individual.fitness(), 1e-9);
    }

    @Test
    public void testFitnessCache() {
        assertEquals(individual.fitness(), individual.fitness(), 1e-18);
    }

    @Test
    public void testGet() {
        assertEquals(chromosome.get(2), individual.get(2));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetOutOfBond() {
        individual.get(individual.size());
    }

    @Test
    public void testSize() {
        assertEquals(chromosome.size(), individual.size());
    }

    @Test(expected=UnsupportedOperationException.class)
    public void chromosomeImmutability() {
        individual.chromosome().set(0,12);
    }

    @Test
    public void testFitnessFunction() {
      assertSame(fixedFitness, individual.fitnessFunction());
    }

    @Test
    public void testToStringContainsFitness() {
      assertTrue( individual.toString().contains( String.valueOf(individual.fitness())));
    }

    @Test
    public void siblingGeneration() {
        List<Integer> newChromosome = new ArrayList<Integer>(chromosome);
        newChromosome.set(0, 124);
        LinearIndividual<Integer> sibling = individual.makeSibling(newChromosome);
        assertEquals( individual.size(), sibling.size() );
        assertEquals( new Integer(124), sibling.get(0));
        for( int i=1; i< individual.size(); i++ ) {
            assertEquals( individual.get(i), sibling.get(i) );
        }
    }
}
