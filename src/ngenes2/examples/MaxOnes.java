package ngenes2.examples;

import java.util.Iterator;
import java.util.Random;
import ngenes2.ClassicInstanciator;
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
import ngenes2.ops.mutator.genes.bool.BooleanRandomMutator;
import ngenes2.ops.selector.KTournament;
import ngenes2.ops.selector.Selector;
import ngenes2.population.BasicPopulation;
import ngenes2.population.Population;

import ngenes2.population.PopulationFactory;
import org.picocontainer.DefaultPicoContainer;

import org.picocontainer.injectors.Provider;
import org.picocontainer.injectors.ProviderAdapter;
import static org.picocontainer.Characteristics.USE_NAMES;

public class MaxOnes {

    private final static Fitness<Boolean> fitFunc = new Fitness<Boolean>() {

        public double compute(Individual<Boolean, ?> individual) {
            double sum = 0.0;
            for (Boolean b : individual.chromosome()) {
                if (!b) {
                    sum += 1.0;
                }
            }
            return sum / individual.size();
        }
    };

    private static void exampleByHand() {
        Random rng = new Random();
        final int indSize = 200;
        final int popSize = 100;
        final int genNum = 50;
        Generator<Boolean, LinearIndividual<Boolean>> gen =
                new Generator<Boolean, LinearIndividual<Boolean>>(
                new LinearIndividual.Factory(),
                fitFunc,
                new RandomBooleanGenerator(rng, indSize));
        Population<Boolean, LinearIndividual<Boolean>> pop =
                new BasicPopulation<Boolean, LinearIndividual<Boolean>>(gen.generate(popSize));
        Selector<LinearIndividual<Boolean>> sel =
                new KTournament<LinearIndividual<Boolean>>(rng, 3);
        Crossover<Boolean, LinearIndividual<Boolean>> co =
                new Crossover<Boolean, LinearIndividual<Boolean>>(new MidBreakCrossover<Boolean>());
        Mutator<Boolean, LinearIndividual<Boolean>> mut = new Mutator<Boolean, LinearIndividual<Boolean>>(
                new PointMutation<Boolean>(rng, new BooleanFlipper()));
        Evolver<Boolean, LinearIndividual<Boolean>> evolver =
                new ClassicEvolver<Boolean, LinearIndividual<Boolean>>(genNum, sel, co, mut);
        evolver.evolve(pop);
        System.out.println("Pouet");
    }

    private static void exampleWithPico() {
        DefaultPicoContainer pico = new DefaultPicoContainer();
        pico.addComponent(new Random());
        pico.addComponent("chromosomeSize", 200);
        pico.addComponent("K", 3);
        pico.addComponent("numberOfGeneration", 50);
        pico.addComponent("populationSize", 50);
        pico.addComponent(LinearIndividual.Factory.class);
        pico.addComponent(fitFunc);
        pico.as(USE_NAMES).addComponent(RandomBooleanGenerator.class);
        pico.addComponent(Generator.class);
        pico.addComponent(BasicPopulation.Factory.class);
        pico.as(USE_NAMES).addComponent(KTournament.class);
        pico.addComponent(BooleanFlipper.class);
        pico.addComponent(PointMutation.class);
        pico.addComponent(Mutator.class);
        pico.addComponent(MidBreakCrossover.class);
        pico.addComponent(Crossover.class);
        pico.as(USE_NAMES).addComponent(ClassicEvolver.class);
        pico.start();
        Generator gen = pico.getComponent(Generator.class);
        Population pop = pico.getComponent(PopulationFactory.class).create(gen, 50);
        Evolver e = pico.getComponent(Evolver.class);
        e.evolve(pop);
    }

    private static void exampleWithClassicInstanciator() {
        ClassicInstanciator inst = new ClassicInstanciator()
                .withParameter("chromosomeSize", 200)
                .withParameter("K", 3)
                .withParameter("numberOfGeneration", 50)
                .withParameter("populationSize", 50)
                .with(LinearIndividual.Factory.class)
                .with(fitFunc)
                .withParametrized(RandomBooleanGenerator.class)
                .withParametrized(KTournament.class)
                .with(BooleanFlipper.class)
                .with(PointMutation.class)
                .with(MidBreakCrossover.class);
        Population result = inst.run(100);
    }

    public static void main(String[] args) {
        exampleWithClassicInstanciator();
    }
}
