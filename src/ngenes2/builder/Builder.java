package ngenes2.builder;

import ngenes2.evolver.Evolver;
import ngenes2.population.Population;

/**
 * Builders allow to easily instanciate GA simulations components. Builders
 * are convenient for trials and experimentation because they take care of all the
 * tedious manual instanciate of components. However they ignore generic parameters
 * and thus are not type-safe. For critical or production code, we strongly advise
 * building all components manually.
 */
public interface Builder {

  /**
   * Builds a population which includes the initial generation.
   * @return A population
   */
  public Population<?,?> population();

  /**
   * Builds an evolver.
   * @return Evolver
   */
  public Evolver<?,?> evolver();

}