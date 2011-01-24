/*
 * This file is part of n-genes2.
 *
 * n-genes2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * n-genes2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with n-genes2.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010, Paradigmatic <paradigmatic@streum.org>
 *
 */

package ngenes2.examples;

import java.util.List;
import ngenes2.builder.BasicDIBuilder;
import ngenes2.evolver.Evolver;
import ngenes2.evolver.monitor.BasicBestMonitor;
import ngenes2.evolver.monitor.NopMonitor;
import ngenes2.evolver.stop.MaxGeneration;
import ngenes2.individual.LinearIndividual;
import ngenes2.individual.generator.integer.RandomIntegerGenerator;
import ngenes2.ops.crossover.MidBreakCrossover;
import ngenes2.ops.mutator.PointMutation;
import ngenes2.ops.mutator.genes.integer.PlusOrMinus;
import ngenes2.ops.selector.KTournament;
import ngenes2.population.Population;
import ngenes2.util.Properties;

/**
 * Example implementation of a GA solving a small KnapSack instance. The
 * GA uses individuals with integers genes representing the amount of
 * each object kind in the bag.
 * @see <a href="http://en.wikipedia.org/wiki/Knapsack_problem">http://en.wikipedia.org/wiki/Knapsack_problem</a>
 */
public class KnapSack {

  /**
   * Knap Sack problem fitness. It must be supplied with two arrays of doubles:
   * (1) an array with the values of objects; (2) an array with the weight of objects.
   * Of course both arrays should have the same size.
   */
  public static class Fitness implements ngenes2.fitness.Fitness<Integer> {

    private final double[] values;
    private final double[] weights;
    private final double maximumWeight;

    /**
     * Sole constructor.
     * @param values Object values.
     * @param weights Object weights.
     * @param maximumWeight Weight capacity of the bag.
     * @throws IllegalArgumentException when values and weight arrays have
     * different size.
     */
    public Fitness(double[] values, double[] weights, double maximumWeight) {
      if (values.length != weights.length) {
        throw new IllegalArgumentException("Values and weights length must match");
      }
      this.values = values;
      this.weights = weights;
      this.maximumWeight = maximumWeight;
    }

    public double compute(List<Integer> chromosome) {
      if( chromosome.size() != weights.length ) {
        throw new IllegalArgumentException("Chromosome length does not" +
                " match values and weights length");
      }
      double value = 0;
      double weight = 0;
      for( int i=0; i<values.length; i++ ) {
        weight += chromosome.get(i)*weights[i];
        if( weight > maximumWeight ) {
          return Double.POSITIVE_INFINITY;
        }
        value += chromosome.get(i)*values[i];
      }
      return 1./value;
    }
  }

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    final int indSize = 5;
    final int popSize = 100;
    final double maxWeight = 321;
    final int maxGen = 500;
    final double[] values = new double[] {1.0, 2.0, 3.0, 4.0, 5.0 };
    final double[] weights = new double[] {4.0, 3.0, 10.0, 14.0, 20.0 };
    Properties props = new Properties().put("chromosome_size", indSize)
            .put("population_size", popSize)
            .put("max_gene_value", 35)
            .put("tournament_size",5)
            .put("max_generation", maxGen);
    BasicDIBuilder builder = new BasicDIBuilder()
            .with(props)
            .with(LinearIndividual.Factory.class)
            .with(new Fitness(values, weights, maxWeight))
            .with(RandomIntegerGenerator.class)
            .with(PlusOrMinus.class)
            .with(KTournament.class)
            .with(MaxGeneration.class)
            .with(MidBreakCrossover.class)
            .with(PointMutation.class)
            .with(BasicBestMonitor.class);
        Evolver evolver = builder.evolver();
        Population pop = builder.population();
        evolver.evolve(pop);
        System.out.println( pop.stats().best() );

  }
}
