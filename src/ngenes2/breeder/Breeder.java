package ngenes2.breeder;

import java.util.List;
import ngenes2.individual.Individual;
import ngenes2.population.Population;

/**
 * A Breeder is responsible for producing children individuals from parents
 * individuals. It is usually called by an {@link Evolver} instance several to
 * build the population next generation. It usually calls crossover and mutation
 * operators.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public interface Breeder<G, I extends Individual<G,I>> {

    /**
     * Return the number of parents needed by the breeder to produce
     * children.
     * @return The number of parents
     */
    public int parentsNumber();

    /**
     * Return the number of children produced by the breeder.
     * @return The number of children
     */
    public int childrenNumber();

    /**
     * Performs the breeder operation. The resulting children are added to the population.
     * If the number of parents does not match the require parents number, an exception
     * will be thrown.
     * @param pop The population where the children will be added
     * @param parents The parents
     */
    public void breed( Population<G,I> pop, List<I> parents );
}
