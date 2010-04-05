package ngenes2.individual.generator;

import java.util.List;

public interface ChromosomeGenerator<G> {
    public List<G> generate();
}
