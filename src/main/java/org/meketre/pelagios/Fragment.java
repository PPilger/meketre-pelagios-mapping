package org.meketre.pelagios;

public class Fragment extends Item {
	private int id;
	private Integer necropolisId;
	
	private String meketreUrl;

	public Fragment(Tupel tupel){
		id = tupel.getIntAttribute(0);
		necropolisId = tupel.getIntAttribute(7);
		
		TermMap termMap = TermMap.getInstance();
		
		if(necropolisId != null) {
			meketreUrl = termMap.getUrl(necropolisId);
		}
		
		getAttributes().put("location", termMap.getText(tupel.getIntAttribute(9)));
		getAttributes().put("necropolis", termMap.getText(tupel.getIntAttribute(7)));
		getAttributes().put("date", termMap.getText(tupel.getIntAttribute(6)));
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
		return "fragment";
	}
}
