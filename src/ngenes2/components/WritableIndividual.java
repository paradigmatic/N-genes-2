package ngenes2.components;


public interface WritableIndividual<G> extends Individual<G> {

    public G set( int i, G value );

}
