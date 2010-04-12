package ngenes2.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable pair of instance. Useful to have a function return two values.
 * @author falcone
 * @param <A> type of first element
 * @param <B> type of second element.
 */
public class Pair<A, B> {

  final A first;
  final B second;

  /**
   * Sole constructor.
   * @param first First element
   * @param second Second element
   */
  public Pair(A first, B second) {
    if (first == null || second == null) {
      throw new NullPointerException("A pair cannot contain null values");
    }
    this.first = first;
    this.second = second;
  }

  /**
   * Get the first element
   * @return the first element
   */
  public A first() {
    return first;
  }

  /**
   * Get the second element
   * @return the second element
   */
  public B second() {
    return second;
  }

  @Override
  public String toString() {
    return "( " + first + ", " + second + " )";
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Pair<?,?>)) {
      return false;
    }
    final Pair<A, B> other = (Pair<A, B>) obj;
    return other.first().equals(this.first) && other.second().equals(this.second);
  }

  @Override
  public int hashCode() {
    return 31 * (7 + first.hashCode()) + second.hashCode();
  }

  /**
   * Convert the pair to a list containing only the pair elements.
   * @return A list
   */
  @SuppressWarnings("unchecked")
  public List<?> toList() {
    List lst = new ArrayList();
    lst.add(first);
    lst.add(second);
    return lst;
  }

  /**
   * Build a pair using two element. Both types are inferred from the arguments.
   * @param <S> First element type.
   * @param <T> Second element type.
   * @param s First element
   * @param t Second element
   * @return A pair
   */
  public static <S, T> Pair<S, T> make(S s, T t) {
    return new Pair<S, T>(s, t);
  }
}
