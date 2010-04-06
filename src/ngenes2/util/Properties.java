package ngenes2.util;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Properties {

    private final Map<String, Double> doubles;
    private final Map<String, Integer> integers;
    private final Map<String, Boolean> booleans;
    private final Map<String, String> strings;

    public Properties() {
        doubles = new HashMap<String, Double>();
        integers = new HashMap<String, Integer>();
        booleans = new HashMap<String, Boolean>();
        strings = new HashMap<String, String>();
    }

    public Properties put( String key, String value ){
        strings.put(key,value);
        return this;
    }

    public Properties put( String key, int value ) {
        integers.put(key, value);
        return this;
    }

    public Properties put( String key, double value ) {
        doubles.put(key, value);
        return this;
    }

    public Properties put( String key, boolean value ) {
        booleans.put(key, value);
        return this;
    }

    public int getInt( String key ) {
        check(integers, key);
        return integers.get(key);
    }

    public double getDouble( String key ) {
        check(doubles, key);
        return doubles.get(key);
    }

    public boolean getBoolean( String key ) {
        check(booleans, key);
        return booleans.get(key);
    }

    public String getString( String key ) {
        check(strings, key);
        return strings.get(key);
    }

    private void check(Map<String,?> map, String key) throws NoSuchElementException {
        if (!map.containsKey(key)) {
            throw new NoSuchElementException("Property " + key + " was not declared");
        }
    }


}
