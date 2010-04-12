package ngenes2.ops.mutator.genes.bool;

import ngenes2.ops.mutator.genes.*;

/**
 * Mutates a boolean gene by flipping its value (using the <i>not</i> logical
 * operator).
 */
public class BooleanFlipper implements GeneMutator<Boolean> {

    public Boolean mutate(Boolean before) {
        return ! before;
    }

}
