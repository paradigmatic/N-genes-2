package ngenes2.population;

import ngenes2.individual.Individual;
import ngenes2.individual.generator.Generator;

/**
 * Creates a new non-empty population using a indivdiual {@link Generator}.
 * @param <G> Gene type
 * @param <I> Individual type
 * @param <P> Population type
 */
public interface PopulationFactory<G, I extends Individual<G, I>, P extends Population<G, I>> {

  /**
   * Create a new population of a given size. The population is filled with
   * indivdiual created by an individual generator.
   * @param gen Individual generator
   * @param popSize Desired population size
   * @return A new population
   */
  public P create(Generator<G, I> gen, int popSize);

}
