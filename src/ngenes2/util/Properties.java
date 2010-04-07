package ngenes2.util;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Properties {

    private static final String INT = "Integer";
    private static final String DOUBLE = "Double";
    private static final String BOOL = "Boolean";
    private static final String STR  = "String";

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

    public Properties put(String key, String value) {
        strings.put(key, value);
        return this;
    }

    public Properties put(String key, int value) {
        integers.put(key, value);
        return this;
    }

    public Properties put(String key, double value) {
        doubles.put(key, value);
        return this;
    }

    public Properties put(String key, boolean value) {
        booleans.put(key, value);
        return this;
    }

    public Properties parse( String key, String value ){
        try {
            Integer i = new Integer( value );
            put( key, i );
        } catch ( NumberFormatException e1 ) {
            try {
                Double d = new Double( value );
                put( key, d );
            } catch ( NumberFormatException e2 ) {
                if( value.toLowerCase().equals("true") ) {
                    put( key, true );
                } else if( value.toLowerCase().equals("false") ) {
                    put( key, false );
                } else {
                    put( key, value );
                }
            }
        }
        return this;
    }

    public int getInt(String key) {
        check(integers, key, INT);
        return integers.get(key);
    }

    public double getDouble(String key) {
        check(doubles, key, DOUBLE);
        return doubles.get(key);
    }

    public boolean getBoolean(String key) {
        check(booleans, key, BOOL);
        return booleans.get(key);
    }

    public String getString(String key) {
        check(strings, key, STR);
        return strings.get(key);
    }

    private void check(Map<String, ?> map, String key, String type) throws NoSuchElementException {
        if (!map.containsKey(key)) {
            throw new NoSuchElementException(type + " roperty '" + key + "' was not declared");
        }
    }
}
