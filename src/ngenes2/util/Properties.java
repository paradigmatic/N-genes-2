package ngenes2.util;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Properties {

  private static final String INT = "Integer";
  private static final String DOUBLE = "Double";
  private static final String BOOL = "Boolean";
  private static final String STR = "String";
  private final Map<String, Object> props;

  public Properties() {
    props = new HashMap<String, Object>();
  }

  public Properties put(String key, String value) {
    props.put(key, value);
    return this;
  }

  public Properties put(String key, int value) {
    props.put(key, value);
    return this;
  }

  public Properties put(String key, double value) {
    props.put(key, value);
    return this;
  }

  public Properties put(String key, boolean value) {
    props.put(key, value);
    return this;
  }

  public int getInt(String key) {
    check(key);
    Object value = props.get(key);
    if (props.get(key) instanceof Integer) {
      return (Integer) value;
    }
    try {
      Integer i = new Integer(value.toString());
      return i;
    } catch (NumberFormatException e1) {
      throw new NumberFormatException("Property " + key + " with value " + value +
              " cannot be coerced to integer");
    }
  }

  public double getDouble(String key) {
    check(key);
    Object value = props.get(key);
    if (props.get(key) instanceof Double) {
      return (Double) value;
    }
    try {
      Double i = new Double(value.toString());
      return i;
    } catch (NumberFormatException e1) {
      throw new NumberFormatException("Property " + key + " with value " + value +
              " cannot be coerced to double.");
    }
  }

  public boolean getBoolean(String key) {
    check(key);
    Object value = props.get(key);
    if (props.get(key) instanceof Boolean) {
      return (Boolean) value;
    }
    String str = value.toString().toLowerCase();
    if (str.equals("true")) {
      return Boolean.TRUE;
    } else if (str.equals("false")) {
      return Boolean.FALSE;
    } else {
      throw new NumberFormatException("Property " + key + " with value " + value +
              " cannot be coerced to boolean.");
    }
  }

  public String getString(String key) {
    check(key);
    return props.get(key).toString();
  }

  public boolean contains(String key) {
    return props.containsKey(key);
  }

  private void check(String key) throws NoSuchElementException {
    if (!props.containsKey(key)) {
      throw new NoSuchElementException("Property '" + key + "' was not declared");
    }
  }

}
