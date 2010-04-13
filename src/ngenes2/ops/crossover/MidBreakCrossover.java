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

package ngenes2.ops.crossover;

import java.util.ArrayList;
import java.util.List;

/**
 * Crosses over to chromosome by splitting each parents in half, and by joining
 * the first half of first individual to the second half of the second indivdiual
 * and vice versa.
 * @param <G> Gene type
 */
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

    public int childrenNumber() {
        return 2;
    }



}
