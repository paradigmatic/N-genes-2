
package ngenes2.ops.crossover;

import java.util.ArrayList;
import java.util.List;


public class MidBreakCrossover<G> implements ChromosomeCrossover<G> {

    public List<List<G>> mate(List<G> chrome1, List<G> chrome2) {
         if( chrome1.size() != chrome2.size() ) {
            throw new IllegalArgumentException( "Chromosomes must have same size.");
        }
        final int breakPoint = chrome1.size() / 2;
        List<G> newChrome1 = new ArrayList<G>( chrome1 );
        List<G> newChrome2 = new ArrayList<G>( chrome2 );
        for( int i = breakPoint; i<chrome1.size(); i++ ) {
            newChrome1.set(i, chrome2.get(i));
            newChrome2.set(i, chrome1.get(i));
        }
        List<List<G>> result = new ArrayList<List<G>>(2);
        result.add( newChrome1 );
        result.add( newChrome2 );
        return result;
    }

}
