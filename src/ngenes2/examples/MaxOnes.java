package ngenes2.examples;

import java.util.Random;
import ngenes2.evolver.ClassicEvolver;
import ngenes2.evolver.Evolver;
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

public class MaxOnes {

    private final static Fitness<Boolean> fitFunc = new Fitness<Boolean>() {
        public double compute(Individual<Boolean, ?> individual) {
            double sum = 0.0;
            for( Boolean b: individual.chromosome() ) {
                if( ! b ) {
                    sum += 1.0;
                }
            }
            return sum / individual.size();
        }
    };

    public static void main(String[] args) {
        Random rng = new Random();
        final int indSize = 20;
        final int popSize = 10;
        final int genNum = 50;
        Generator<Boolean,LinearIndividual<Boolean>> gen =
                new Generator<Boolean, LinearIndividual<Boolean>>(
                new LinearIndividual.Factory(),
                fitFunc,
                new RandomBooleanGenerator(rng, indSize)
                );
        Population<Boolean,LinearIndividual<Boolean>> pop =
                new BasicPopulation<Boolean, LinearIndividual<Boolean>>( gen.generate(popSize ));
        Selector<LinearIndividual<Boolean>> sel = 
                new KTournament<LinearIndividual<Boolean>>(rng,3);
        Crossover<Boolean,LinearIndividual<Boolean>> co =
                new Crossover<Boolean, LinearIndividual<Boolean>>( new MidBreakCrossover<Boolean>() );
        Mutator<Boolean,LinearIndividual<Boolean>> mut = new Mutator<Boolean, LinearIndividual<Boolean>>(
                    new PointMutation<Boolean>( rng, new BooleanFlipper() )
                );
        Evolver<Boolean,LinearIndividual<Boolean>> evolver =
                new ClassicEvolver<Boolean, LinearIndividual<Boolean>>(genNum, sel, co, mut);
        evolver.evolve(pop);
        System.out.println("Pouet");
    }

}
