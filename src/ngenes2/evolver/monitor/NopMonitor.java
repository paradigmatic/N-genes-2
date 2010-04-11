package ngenes2.evolver.monitor;

import ngenes2.population.Population;

/**
 * This generation monitor does nothing. Should be passed instead of null
 * when a generation monitor is needed by a component.
 * @author falcone
 */
public class NopMonitor implements GenerationMonitor {

    public void newGeneration(int generationNumber, Population pop) {
        return;
    }

}
