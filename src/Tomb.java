
public class Tomb extends Annotation {
	private int id;
	private Integer necropolisId;
	
	private String meketreUrl;

	public Tomb(Tupel tupel) {
		id = tupel.getIntAttribute(0);
		necropolisId = tupel.getIntAttribute(7);
		
		TermMap termMap = TermMap.getInstance();
		
		if(necropolisId != null) {
			meketreUrl = TermMap.getInstance().getUrl(necropolisId);
		}

		getAttributes().put("necropolis", termMap.getText(tupel.getIntAttribute(7)));
		getAttributes().put("tombNumber", tupel.getStringAttribute(6));
		getAttributes().put("date", termMap.getText(tupel.getIntAttribute(9)));
		getAttributes().put("owner", termMap.getText(tupel.getIntAttribute(8)));
	}

	@Override
	public int getId() {
		return id;
	}

	public Integer getNecropolisID() {
		return necropolisId;
	}

	@Override
	public String getMeketreUrl() {
		return meketreUrl;
	}

	@Override
	public String getType() {
		return "tomb";
	}
}
