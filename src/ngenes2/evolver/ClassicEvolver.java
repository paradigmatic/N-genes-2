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

package ngenes2.evolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ngenes2.evolver.monitor.GenerationMonitor;
import ngenes2.evolver.stop.StopCondition;
import ngenes2.individual.Individual;
import ngenes2.breeder.Breeder;
import ngenes2.ops.selector.Selector;
import ngenes2.population.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The classic evolver implement the usual GA evolution. It uses several other components
 * to prametrize the evolution (see the constructor). Individual of the population are first
 * selected with a {@link Selector}, then passed to a {@link Breeder} which creates children
 * and add them to the population next generation. Before each generation a {@link StopCondition} is
 * called to check for termination. After each generation a {@link GenerationMonitor} is called to
 * allow additional action to take place such as logging or customized population manipulation.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public class ClassicEvolver<G,I extends Individual<G,I>> implements Evolver <G,I>{

    private final Logger logger = LoggerFactory.getLogger(ClassicEvolver.class);

    private final Selector<I> selector;
    private final Breeder<G,I> breeder;
    private final GenerationMonitor<G,I> monitor;
    private final StopCondition<G,I> stopCondition;

    /**
     * Create a ClassicEvolver instance using the provided components.
     * @param selector A selector responsible for drawing parents individual to mate.
     * @param breeder A breeder which creates children from parents.
     * @param monitor A generation monitor which is called at the end of every iteration.
     * @param stopCondition A stop condition which is called before iteration.
     */
    public ClassicEvolver(Selector<I> selector, Breeder<G, I> breeder, GenerationMonitor<G, I> monitor, StopCondition<G, I> stopCondition) {
        this.selector = selector;
        this.breeder = breeder;
        this.monitor = monitor;
        this.stopCondition = stopCondition;
    }

    public void evolve(Population<G,I> population) {
        logger.info("Starting evolution");
        int t=0;
        while( ! stopCondition.shouldStop(t, population) ) {
            logger.debug("Generation: {}", t);
            int count = 0;
            final int toDraw = population.size()/breeder.childrenNumber() * breeder.parentsNumber();
            Iterator<I> selected = selector.select(toDraw, population);
            while( count < population.size() ) {
                final List<I> parents = new ArrayList<I>(breeder.parentsNumber());
                for( int i=0; i < breeder.parentsNumber(); i++ ) {
                    parents.add( selected.next() );
                }
                breeder.breed(population, parents);
                count += breeder.childrenNumber();
            }
            population.nextGeneration();
            monitor.newGeneration(t, population);
            t++;
        }
        logger.info("Evolution finished");
    }


}
