package ngenes2.components;


public interface Individual<G> {

    public double fitness();

    public int size();

    public G get( int i );

}
