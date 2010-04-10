package ngenes2.util;

import java.util.ArrayList;
import java.util.List;

public class Pair<A,B> {

    final A first;
    final B second;

    public Pair(A first, B second) {
        if( first == null || second == null ) {
            throw new NullPointerException("A pair cannot contain null values");
        }
        this.first = first;
        this.second = second;
    }

    public A first(){ return first; }
    public B second() { return second; }

    @Override
    public String toString() {
        return "( "+first+", "+second+" )";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! ( obj instanceof Pair )) {
            return false;
        }
        final Pair<A, B> other = (Pair<A, B>) obj;
        return other.first().equals(this.first) && other.second().equals(this.second);
    }

    @Override
    public int hashCode() {
        return 31*(7 + first.hashCode() ) + second.hashCode();
    }

    @SuppressWarnings("unchecked")
    public List<?> toList() {
        List lst = new ArrayList();
        lst.add(first);
        lst.add(second);
        return lst;
    }

    public static <S,T> Pair<S,T> make( S s, T t ) {
        return new Pair<S,T>(s,t);
    }


}
