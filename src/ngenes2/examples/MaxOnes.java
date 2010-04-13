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
import java.util.Random;
import ngenes2.builder.BasicDIBuilder;
import ngenes2.breeder.Breeder;
import ngenes2.breeder.ClassicalBreeder;
import ngenes2.builder.Builder;
import ngenes2.evolver.ClassicEvolver;
import ngenes2.evolver.Evolver;
import ngenes2.evolver.monitor.GenerationMonitor;
import ngenes2.evolver.stop.FitnessTarget;
import ngenes2.evolver.stop.MaxGeneration;
import ngenes2.evolver.stop.StopCondition;
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
import ngenes2.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MaxOnes is a classical GA toy problem, considered as a good introductory
 * problem. The goal is to "optimized" random boolean individuals until
 * all genes have a true value.
 */
public class MaxOnes {

    private final static Logger logger = LoggerFactory.getLogger(MaxOnes.class);

    /**
     * MaxOnes Fitness. Simply count the number of False genes and divide the
     * count by the chromosome size.
     */
    public static class Fitness implements ngenes2.fitness.Fitness<Boolean> {
        public double compute(List<Boolean> chromosome) {
            double sum = 0.0;
            for( Boolean b: chromosome ) {
                if( ! b ) {
                    sum += 1.0;
                }
            }
            return sum / chromosome.size();
        }
    }

    /**
     * This monitor logs the best individual of each generation.
     */
    public final static GenerationMonitor<Boolean,LinearIndividual<Boolean>> monitor
            = new GenerationMonitor<Boolean, LinearIndividual<Boolean>>() {
        public void newGeneration(int generationNumber, Population<Boolean, LinearIndividual<Boolean>> pop) {
            LinearIndividual<Boolean> best = pop.stats().best();
            logger.info("Generation {}: best individual fitness = {}", generationNumber, best.fitness());
        }
    };

    /**
     * Example of maxones setting with handmade wiring of components.
     */
    private static void exampleByHand() {
        Random rng = new Random();
        final int indSize = 20;
        final int popSize = 100;
        final int genNum = 50;
        Properties props = new Properties()
                .put("tournament_size",3)
                .put("population_size",20)
                .put("chromosome_size", indSize)
                .put("fitness_target", 10e-9)
                .put("max_generation", genNum);

        Generator<Boolean,LinearIndividual<Boolean>> gen =
                new Generator<Boolean, LinearIndividual<Boolean>>(
                new LinearIndividual.Factory<Boolean>(),
                new Fitness(),
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
        StopCondition<Boolean,LinearIndividual<Boolean>> stop =
                new FitnessTarget<Boolean,LinearIndividual<Boolean>>(props)
                .or( new MaxGeneration<Boolean,LinearIndividual<Boolean>>(props) );
        Breeder<Boolean,LinearIndividual<Boolean>> breeder = 
                new ClassicalBreeder<Boolean,LinearIndividual<Boolean>>(co, mut);
        Evolver<Boolean,LinearIndividual<Boolean>> evolver =
                new ClassicEvolver<Boolean, LinearIndividual<Boolean>>(sel, breeder, monitor,stop);
        evolver.evolve(pop);
    }

    /**
     * Example of maxones setting using automatic wiring.
     */
    @SuppressWarnings("unchecked")
    private static void exampleWithDIBuilder() {
        Properties prop = new Properties()
                .put("tournament_size",3)
                .put("chromosome_size", 200)
                .put("population_size", 100)
                .put("fitness_target", 10e-9)
                .put("max_generation", 500);
        Builder builder = new BasicDIBuilder()
                .with(prop)
                .with(LinearIndividual.Factory.class)
                .with(new Fitness())
                .with(RandomBooleanGenerator.class)
                .with(KTournament.class)
                .with(BooleanFlipper.class)
                .with(PointMutation.class)
                .with(MidBreakCrossover.class)
                .with(monitor)
                .with( new FitnessTarget(prop).or( new MaxGeneration(prop) ) );
        Evolver evolver = builder.evolver();
        Population pop = builder.population();
        evolver.evolve(pop);
        System.out.println( pop.stats().best() );
    }
    

    public static void main(String[] args) {
        //exampleByHand();
        exampleWithDIBuilder();
    }
}
