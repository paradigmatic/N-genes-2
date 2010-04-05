package ngenes2.components;

import ngenes2.individual.Individual;
import ngenes2.individual.LinearIndividual;
import ngenes2.individual.Individual;
import java.util.ArrayList;
import java.util.List;
import ngenes2.fitness.Fitness;
import ngenes2.ops.mutator.ChromosomeMutator;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.INITIALIZE;
import static org.junit.Assert.*;

public class LinearIndividualTest {

    private Fitness<Integer> fixedFitness = new Fitness<Integer>() {

        public double compute(Individual<Integer> individual) {
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
    public void testGet() {
        assertEquals(chromosome.get(2), individual.get(2));
    }

    @Test
    public void testSize() {
        assertEquals(chromosome.size(), individual.size());
    }

    @Test
    public void testMutate() {
        final ChromosomeMutator<Integer> mutator = new ChromosomeMutator<Integer>() {
            public List<Integer> mutate(List<Integer> chromosome) {
                List<Integer> lst = new ArrayList<Integer>( chromosome );
                lst.set(0, 15);
                return lst;
            }
        };
        final Individual<Integer> newInd = individual.mutate(mutator);
        assertEquals( new Integer(0), individual.get(0));
        assertEquals( new Integer(15), newInd.get(0));
        assertEquals( individual.get(1), newInd.get(1));
    }
}
