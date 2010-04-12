package ngenes2.builder;

import ngenes2.evolver.Evolver;
import ngenes2.evolver.monitor.NopMonitor;
import ngenes2.evolver.stop.FitnessTarget;
import ngenes2.evolver.stop.MaxGeneration;
import ngenes2.examples.MaxOnes;
import ngenes2.individual.LinearIndividual;
import ngenes2.individual.generator.bool.RandomBooleanGenerator;
import ngenes2.ops.crossover.MidBreakCrossover;
import ngenes2.ops.mutator.PointMutation;
import ngenes2.ops.mutator.genes.bool.BooleanFlipper;
import ngenes2.ops.selector.KTournament;
import ngenes2.population.Population;
import ngenes2.util.Properties;
import org.junit.Test;
import static org.junit.Assert.*;

public class BasicDIBuilderTest {

  @Test
  public void testInstanciation() {
    Properties prop = new Properties()
                .put("tournament_size",3)
                .put("chromosome_size", 200)
                .put("population_size", 100)
                .put("fitness_target", 10e-9)
                .put("max_generation", 500);
    BasicDIBuilder builder = new BasicDIBuilder()
                .with(prop)
                .with(LinearIndividual.Factory.class)
                .with(new MaxOnes.Fitness())
                .with(RandomBooleanGenerator.class)
                .with(KTournament.class)
                .with(BooleanFlipper.class)
                .with(PointMutation.class)
                .with(MidBreakCrossover.class)
                .with(NopMonitor.class)
                .with( new FitnessTarget(prop).or( new MaxGeneration(prop) ) );
    Evolver ev = builder.evolver();
    Population pop = builder.population();
    assertTrue( ev != null );
    assertTrue( pop != null );
  }
}