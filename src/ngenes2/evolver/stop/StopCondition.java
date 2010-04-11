package ngenes2.evolver.stop;

import ngenes2.individual.Individual;
import ngenes2.population.Population;

/**
 * This class represents a stop condition which, when fulfilled, stop the
 * evolution. Stop conditions can be chained using the logical operators <i>or</i> and <i>and</i>
 * to produce richer conditions.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public interface StopCondition<G, I extends Individual<G, I>> {

  /**
   * This method returns true when the condition is fulfilled.
   * @param generation The generation count. The first generation is 0.
   * @param population The population being evolved.
   * @return True if and only if the evolution should stop.
   */
  public boolean shouldStop(int generation, Population<G, I> population);

  /**
   * Chains the current condition with an other one, using the or logical
   * operator. The resultingcondition returns true if one of the condition are true.
   * There are no warranties than the second condition will be actually executed if the first
   * is true.
   * @param that Another condition.
   * @return A stop condition which will return <pre>(this or that)</pre>.
   */
  public StopCondition<G, I> or(StopCondition<G, I> that);

  /**
   * Chains the current condition with an other one, using the and logical operator. The resulting
   * condition returns true if both conditions are true. There are no
   * warranties than the second condition will be actually executed if the first
   * is false.
   * @param that Another condition.
   * @return A stop condition which will return <pre>(this and that)</pre>.
   */
  public StopCondition<G, I> and(StopCondition<G, I> that);
}
