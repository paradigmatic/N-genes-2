package ngenes2.evolver.monitor;

import ngenes2.population.Population;

public class NopMonitor implements GenerationMonitor {

    public void newGeneration(int generationNumber, Population pop) {
        return;
    }

}
