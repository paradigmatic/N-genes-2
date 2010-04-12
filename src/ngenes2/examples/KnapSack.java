package ngenes2.examples;

import java.util.List;
import ngenes2.builder.BasicDIBuilder;
import ngenes2.evolver.Evolver;
import ngenes2.evolver.stop.MaxGeneration;
import ngenes2.individual.LinearIndividual;
import ngenes2.individual.generator.integer.RandomIntegerGenerator;
import ngenes2.ops.crossover.MidBreakCrossover;
import ngenes2.ops.mutator.PointMutation;
import ngenes2.ops.mutator.genes.integer.PlusOrMinus;
import ngenes2.ops.selector.KTournament;
import ngenes2.population.Population;
import ngenes2.util.Properties;

public class KnapSack {

  public static class Fitness implements ngenes2.fitness.Fitness<Integer> {

    private final double[] values;
    private final double[] weights;
    private final double maximumWeight;

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
    final int indSize = 4;
    final int popSize = 100;
    final double maxWeight = 200;
    final int maxGen = 500;
    final double[] values = new double[] {1.0, 2.0, 3.0, 4.0 };
    final double[] weights = new double[] {2.0, 1.0, 10.0, 14.0 };
    Properties props = new Properties().put("chromosome_size", indSize)
            .put("population_size", popSize)
            .put("gene-max", 50)
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
            .with(MaxOnes.monitor);
        Evolver evolver = builder.evolver();
        Population pop = builder.population();
        evolver.evolve(pop);
        System.out.println( pop.stats().best() );

  }
}
