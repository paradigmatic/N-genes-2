package ngenes2.individual.generator.bool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ngenes2.individual.generator.ChromosomeGenerator;

public class RandomBooleanGenerator implements ChromosomeGenerator<Boolean> {

    final private Random rng;
    final private int size;

    public RandomBooleanGenerator(Random rng, int size) {
        this.rng = rng;
        this.size = size;
    }

    private Boolean randomValue() {
        return rng.nextBoolean() ? Boolean.FALSE : Boolean.TRUE;
    }

    public List<Boolean> generate() {
        List<Boolean> chrome = new ArrayList<Boolean>(size);
        for( int i=0; i<size; i++ ) {
            chrome.add( randomValue() );
        }
        return chrome;
    }

}
