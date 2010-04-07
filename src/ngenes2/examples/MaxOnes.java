package ngenes2.examples;

import java.util.List;
import java.util.Random;
import ngenes2.evolver.ClassicEvolver;
import ngenes2.evolver.Evolver;
import ngenes2.evolver.GenerationMonitor;
import ngenes2.fitness.Fitness;
import ngenes2.individual.Individual;
import ngenes2.individual.LinearIndividual;
import ngenes2.individual.generator.Generator;
import ngenes2.individual.generator.bool.RandomBooleanGenerator;
import ngenes2.ops.crossover.Crossover;
import ngenes2.ops.crossover.MidBreakCrossover;
import ngenes2.ops.mutator.Mutator;
import ngenes2.ops.mutator.PointMutation;
import ngenes2.ops.mutator.genes.bool.BooleanFlipper;
import ngenes2.ops.selector.KTournament;
import ngenes2.ops.selector.Selector;
import ngenes2.population.BasicPopulation;
import ngenes2.population.Population;
import ngenes2.population.Stats;
import ngenes2.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaxOnes {

    private final static Logger logger = LoggerFactory.getLogger(MaxOnes.class);

    private final static Fitness<Boolean> fitFunc = new Fitness<Boolean>() {
        public double compute(List<Boolean> chromosome) {
            double sum = 0.0;
            for( Boolean b: chromosome ) {
                if( ! b ) {
                    sum += 1.0;
                }
            }
            return sum / chromosome.size();
        }
    };

    private final static GenerationMonitor<Boolean,LinearIndividual<Boolean>> monitor
            = new GenerationMonitor<Boolean, LinearIndividual<Boolean>>() {
        public void newGeneration(int generationNumber, Population<Boolean, LinearIndividual<Boolean>> pop) {
            LinearIndividual<Boolean> best = new Stats<LinearIndividual<Boolean>>(pop).best();
            logger.info("Generation {}: best individual fitness = {}", generationNumber, best.fitness());
        }
    };


    public static void main(String[] args) {
        Random rng = new Random();
        Properties props = new Properties().put("tournament_size",3).
                put("chromosome_size", 2000).
                put("generations", 50).
                put("population_size",10);
        Generator<Boolean,LinearIndividual<Boolean>> gen =
                new Generator<Boolean, LinearIndividual<Boolean>>(
                new LinearIndividual.Factory(),
                fitFunc,
                new RandomBooleanGenerator(rng, props)
                );
        Population<Boolean,LinearIndividual<Boolean>> pop =
                new BasicPopulation<Boolean, LinearIndividual<Boolean>>( 
                  gen.generate(props.getInt("population_size"))
                );

        Selector<LinearIndividual<Boolean>> sel = 
                new KTournament<LinearIndividual<Boolean>>(rng,props);
        Crossover<Boolean,LinearIndividual<Boolean>> co =
                new Crossover<Boolean, LinearIndividual<Boolean>>( new MidBreakCrossover<Boolean>() );
        Mutator<Boolean,LinearIndividual<Boolean>> mut = new Mutator<Boolean, LinearIndividual<Boolean>>(
                    new PointMutation<Boolean>( rng, new BooleanFlipper() )
                );
        Evolver<Boolean,LinearIndividual<Boolean>> evolver =
                new ClassicEvolver<Boolean, LinearIndividual<Boolean>>(props, sel, co, mut, monitor);
        evolver.evolve(pop);
        System.out.println("Pouet");
    }

}
