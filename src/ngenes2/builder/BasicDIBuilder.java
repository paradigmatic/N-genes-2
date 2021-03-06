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

package ngenes2.builder;

import java.util.Random;
import ngenes2.breeder.ClassicalBreeder;
import ngenes2.evolver.ClassicEvolver;
import ngenes2.evolver.Evolver;
import ngenes2.individual.generator.Generator;
import ngenes2.ops.crossover.Crossover;
import ngenes2.ops.mutator.Mutator;
import ngenes2.population.BasicPopulation;
import ngenes2.population.Population;
import ngenes2.population.PopulationFactory;
import ngenes2.util.Properties;
import org.picocontainer.DefaultPicoContainer;

/**
 * <p>BasicDIBuilder builds a standard GA simulation using dependency injection.
 * Components must be provided as classes or instances using <i>with()</i>
 * methods.
 * </p>
 * <p>BasicDIBuilder uses the following components:
 * <ul>
 * <li>{@link java.util.Random}</li>
 * <li>{@link ngenes2.individual.generator.Generator}</li>
 * <li>{@link ngenes2.ops.mutator.Mutator}</li>
 * <li>{@link ngenes2.ops.crossover.Crossover}</li>
 * <li>{@link ngenes2.evolver.ClassicEvolver}</li>
 * <li>{@link ngenes2.breeder.ClassicalBreeder}</li>
 * </ul>
 * All other components must be registered using the <i>with()</i> methods.
 */
public class BasicDIBuilder implements Builder {

  private final DefaultPicoContainer pico;
  private boolean created = false;

  /**
   * Sole constructor.
   */
  public BasicDIBuilder() {
    pico = new DefaultPicoContainer();
  }

  /**
   * Register a component using a class.
   * @param klass A class component.
   * @return The current instance for method chaining.
   */
  public <T> BasicDIBuilder with(Class<T> klass) {
    pico.addComponent(klass);
    return this;
  }

  /**
   * Register a component using an instance
   * @param o The instance to register.
   * @return The current instance for method chaining.
   */
  public BasicDIBuilder with(Object o) {
    pico.addComponent(o);
    return this;
  }

  private void create() {
    if (!created) {
      pico.addComponent(new Random());
      pico.addComponent(Generator.class);
      pico.addComponent(BasicPopulation.Factory.class);
      pico.addComponent(Mutator.class);
      pico.addComponent(Crossover.class);
      pico.addComponent(ClassicEvolver.class);
      pico.addComponent(ClassicalBreeder.class);
      pico.start();
      created = true;
    }
  }

  @SuppressWarnings("unchecked")
  public Population<?, ?> population() {
    create();
    final int popSize = pico.getComponent(Properties.class).getInt("population_size");
    final Generator<?,?> gen = pico.getComponent(Generator.class);
    return pico.getComponent(PopulationFactory.class).create(gen, popSize);


  }
  
  @SuppressWarnings("unchecked")
  public Evolver<?, ?> evolver() {
    create();
    return pico.getComponent(Evolver.class);
  }
}
