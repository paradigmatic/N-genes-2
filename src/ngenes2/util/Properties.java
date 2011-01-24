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

package ngenes2.util;

import java.util.HashMap;
import java.util.Iterator;
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

    /**
     * Adds all the keys and value from another property instance into the current one.
     * Existing properties with the same name with be overwritten,
     * @param that The "source" property instance. Will be copied but not modified
     * @return This property object with new properties added.
     */
  public Properties putAll( Properties that ) {
      for( String k: that.props.keySet() ) {
          this.props.put( k, that.props.get(k) );
      }
      return this;
  }

  private void check(String key) throws NoSuchElementException {
    if (!props.containsKey(key)) {
      throw new NoSuchElementException("Property '" + key + "' was not declared");
    }
  }
}
