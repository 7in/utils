/**
 * 
 */
package com.simon7in.strategy.process.context;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AbstractContext implements Context {

	private Map<String, Object> attributes = new HashMap<String, Object>();

	public int size() {
		return attributes.size();
	}

	public boolean isEmpty() {
		return attributes.isEmpty();
	}

	public boolean containsKey(Object key) {
		return attributes.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return attributes.containsValue(value);
	}

	public Object get(Object key) {
		return attributes.get(key);
	}

	public Object put(String key, Object value) {
		return attributes.put(key, value);
	}

	public Object remove(Object key) {
		return attributes.remove(key);
	}

	public void putAll(Map<? extends String, ? extends Object> m) {
		attributes.putAll(m);
	}

	public void clear() {
		attributes.clear();
	}

	public Set<String> keySet() {
		return attributes.keySet();
	}

	public Collection<Object> values() {
		return attributes.values();
	}

	public Set<Entry<String, Object>> entrySet() {
		return attributes.entrySet();
	}

	public boolean equals(Object o) {
		return attributes.equals(o);
	}

	public int hashCode() {
		return attributes.hashCode();
	}

}
