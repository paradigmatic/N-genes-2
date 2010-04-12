package ngenes2.util;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Holds property for configuration purpose. This class is different from
 * {@link java.util.Properties} because when the property are accessed, they
 * can be converted in an boolean, int or double variable. Put() method
 * return the current properties object to allow method chaining.
 */
public class Properties {

  private final Map<String, Object> props;

  /**
   * Builds an empty properties object.
   */
  public Properties() {
    props = new HashMap<String, Object>();
  }

  /**
   * Add a string property.
   * @param key Key
   * @param value Value
   * @return The properties instance.
   */
  public Properties put(String key, String value) {
    props.put(key, value);
    return this;
  }

  /**
   * Add an int property.
   * @param key Key
   * @param value Value
   * @return The properties instance.
   */
  public Properties put(String key, int value) {
    props.put(key, value);
    return this;
  }

  /**
   * Add a double property.
   * @param key Key
   * @param value Value
   * @return The properties instance.
   */
  public Properties put(String key, double value) {
    props.put(key, value);
    return this;
  }

  /**
   * Add a boolean property.
   * @param key Key
   * @param value Value
   * @return The properties instance.
   */
  public Properties put(String key, boolean value) {
    props.put(key, value);
    return this;
  }

  /**
   * Get an int property
   * @param key Key
   * @return Value
   */
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

  /**
   * Get a double property
   * @param key Key
   * @return Value
   */
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

  /**
   * Get a boolean property
   * @param key Key
   * @return Value
   */
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

  /**
   * Get a string property
   * @param key Key
   * @return Value
   */
  public String getString(String key) {
    check(key);
    return props.get(key).toString();
  }

  /**
   * Check if a properties instance contains a given key.
   * @param key The key to be checked
   * @return True if the properties contains the given key.
   */
  public boolean contains(String key) {
    return props.containsKey(key);
  }

  private void check(String key) throws NoSuchElementException {
    if (!props.containsKey(key)) {
      throw new NoSuchElementException("Property '" + key + "' was not declared");
    }
  }
}
