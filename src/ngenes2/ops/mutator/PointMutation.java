package ngenes2.ops.mutator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ngenes2.ops.mutator.genes.GeneMutator;


public class PointMutation<G> implements ChromosomeMutator<G> {

    private final Random rng;
    private final GeneMutator<G> geneMutator;

    public PointMutation(Random rng, GeneMutator<G> geneMutator ) {
        this.rng = rng;
        this.geneMutator = geneMutator;
    }

    public List<G> mutate(List<G> chromosome) {
        final int position = rng.nextInt( chromosome.size() );
        final List<G> newChromosome = new ArrayList<G>( chromosome );
        final G oldValue = chromosome.get(position);
        final G newValue = geneMutator.mutate(oldValue);
        newChromosome.set(position, newValue);
        return newChromosome;
    }

}
