package ngenes2.ops.mutator.genes.bool;

import ngenes2.ops.mutator.genes.*;
import java.util.Random;

//TODO: tests

/**
 * Mutates a single boolean gene at random using an equal probability. Resulting
 * gene has 50% probability of being true.
 */
public class BooleanRandomMutator implements GeneMutator<Boolean> {

    final private Random rng;

    /**
     * Sole constructor.
     * @param rng A random number generator
     */
    public BooleanRandomMutator(Random rng) {
        this.rng = rng;
    }

    public Boolean mutate(Boolean before) {
        return rng.nextDouble() > 0.5 ? Boolean.TRUE : Boolean.FALSE;
    }



}
