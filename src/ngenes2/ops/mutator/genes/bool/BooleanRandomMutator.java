package ngenes2.ops.mutator.genes.bool;

import ngenes2.ops.mutator.genes.*;
import java.util.Random;

//TODO: tests
public class BooleanRandomMutator implements GeneMutator<Boolean> {

    final private Random rng;

    public BooleanRandomMutator(Random rng) {
        this.rng = rng;
    }

    public Boolean mutate(Boolean before) {
        return rng.nextDouble() > 0.5 ? Boolean.TRUE : Boolean.FALSE;
    }



}
