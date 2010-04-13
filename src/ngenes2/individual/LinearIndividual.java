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

package ngenes2.individual;

import java.util.Collections;
import java.util.List;
import ngenes2.fitness.Fitness;

/**
 * This class represents the classic basic individual used in most GA settings.
 * They are immutable and cache their fitness value.
 * @param <G> Gene type
 */
public class LinearIndividual<G> implements Individual<G,LinearIndividual<G>> {

    private final List<G> genes;
    private final Fitness<G> fitFunc;
    private boolean fitnessOK = false;
    private double fitnessValue = 0.0;

    /**
     * Sole constructor.
     * @param fitness A fitness function
     * @param genes A list of genes
     */
    public LinearIndividual( Fitness<G> fitness, List<G> genes ) {
        this.genes = Collections.unmodifiableList(genes);
        this.fitFunc = fitness;
    }

    public double fitness() {
        if( ! fitnessOK ) {
            fitnessValue = fitFunc.compute(genes);
            fitnessOK = true;
        }
        return fitnessValue;
    }

    public G get(int i) {
        if( i > genes.size() ) {
            throw new IndexOutOfBoundsException();
        }
        return genes.get(i);
    }

    public int size() {
        return genes.size();
    }

    public List<G> chromosome() {
        return genes;
    }

    public LinearIndividual<G> makeSibling(List<G> newChromosome) {
        return new LinearIndividual<G>( fitFunc, newChromosome );
    }

    public Fitness<G> fitnessFunction() {
        return fitFunc;
    }

  @Override
  public String toString() {
    return "Individual(fitness="+fitness()+", chromosom="+chromosome().toString()+")";
  }

  /**
   * An {@link IndividualFactory} which creates linear individuals.
   * @param <G> Gene tyoe
   */
    public static class Factory<G> implements IndividualFactory<G,LinearIndividual<G>> {
        public LinearIndividual<G> makeIndividual(Fitness<G> fitFunc, List<G> chromosome) {
            return new LinearIndividual<G>(fitFunc,chromosome);
        }
    }
}
