package ngenes2.individual;


public interface Individual<G> {

    public double fitness();

    public int size();

    public G get( int i );

}
