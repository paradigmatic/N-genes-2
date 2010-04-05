package ngenes2.individual.generator;

import java.util.List;

interface ChromosomeGenerator<G> {
    public List<G> generate();
}
