import java.util.List;

public class Theme extends Annotation {
	private int id;
	private Integer originId;

	private Annotation origin;

	public Theme(Tupel tupel, List<Annotation> annotations) {
		id = tupel.getIntAttribute(0);
		originId = tupel.getIntAttribute(8);
		
		TermMap termMap = TermMap.getInstance();

		if (originId != null) {
			for (Annotation a : annotations) {
				if (a.getId() == originId) {
					origin = a;
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
