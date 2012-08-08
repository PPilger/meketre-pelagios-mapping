package org.meketre.pelagios;
import java.util.HashMap;
import java.util.Map;


public class TermMap {
	private static TermMap instance;
	
	private Map<Integer, String> textMap = new HashMap<Integer, String>();
	private Map<Integer, String> urlMap = new HashMap<Integer, String>();
	
	private TermMap() {
	}
	
	public static TermMap getInstance() {
		if(instance == null) {
			instance = new TermMap();
		}
		return instance;
	}
	
	public void putTerm(Tupel tupel) {
		int id = tupel.getIntAttribute(0);
		textMap.put(id, tupel.getStringAttribute(1));
		urlMap.put(id, tupel.getStringAttribute(2));
	}
	
	public String getUrl(int id) {
		return urlMap.get(id);
	}

	public String getText(int id) {
		return textMap.get(id);
	}
	
	public String getText(Integer id) {
		if (id == null) {
			return null;
		}
		return textMap.get(id);
	}
}
