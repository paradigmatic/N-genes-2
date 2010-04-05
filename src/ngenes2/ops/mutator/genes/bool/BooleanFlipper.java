package ngenes2.ops.mutator.genes.bool;

import ngenes2.ops.mutator.genes.*;

public class BooleanFlipper implements GeneMutator<Boolean> {

    public Boolean mutate(Boolean before) {
        return ! before;
    }

}
