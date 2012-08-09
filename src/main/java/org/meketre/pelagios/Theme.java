package org.meketre.pelagios;
import java.util.List;

public class Theme extends Item {
	private int id;
	private Integer originId;

	private Item origin;

	public Theme(Tupel tupel, List<Item> items) {
		id = tupel.getIntAttribute(0);
		originId = tupel.getIntAttribute(8);
		
		TermMap termMap = TermMap.getInstance();

		if (originId != null) {
			for (Item item : items) {
				if (item.getId() == originId) {
					origin = item;
					break;
				}
			}
		}

		if(origin != null) {
			getAttributes().put("owner", origin.getAttributes().get("owner"));
			getAttributes().put("necropolis", origin.getAttributes().get("necropolis"));
			getAttributes().put("date", origin.getAttributes().get("date"));
			getAttributes().put("location", origin.getAttributes().get("location"));
		}
		getAttributes().put("execution",termMap.getText(tupel.getIntAttribute(7)));
		getAttributes().put("position",tupel.getStringAttribute(6));
	}

	@Override
	public int getId() {
		return id;
	}

	public Integer getOriginID() {
		return originId;
	}

	public String getMeketreUrl() {
		if (origin != null) {
			return origin.getMeketreUrl();
		}
		return null;
	}

	@Override
	public String getType() {
		return "theme";
	}
}
