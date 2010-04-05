

package ngenes2.individual.generator;

import java.util.Iterator;
import ngenes2.fitness.Fitness;
import ngenes2.individual.IndividualFactory;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class GeneratorTest {

    private IndividualFactory indFac;
    private Fitness fitFunc;
    private ChromosomeGenerator chromeGen;
    private Generator gen;

    @Before
    public void setup() {
        indFac = mock(IndividualFactory.class);
        fitFunc = mock(Fitness.class);
        chromeGen = mock(ChromosomeGenerator.class);
        gen = new Generator(indFac, fitFunc, chromeGen);
    }


    @Test
    public void testGenerate() {
        gen.generate();
        verify( indFac, times(1) ).makeIndividual(eq(fitFunc), anyList());
        verify( chromeGen, times(1) ).generate();
    }

    @Test
    public void testGenerate_int() {
        final int n = 12;
        Iterator it = gen.generate(n);
        while( it.hasNext() ) {
            it.next();
        }
        verify( indFac, times(n) ).makeIndividual(eq(fitFunc), anyList());
        verify( chromeGen, times(n) ).generate();
    }

}