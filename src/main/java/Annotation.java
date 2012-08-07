import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Annotation {
	private String pelagiosUrl;
	private Map<String, String> attributes = new LinkedHashMap<String, String>();

	public abstract int getId();

	public abstract String getType();

	public String getTitle() {
		String title = "";

		for (String attribute : attributes.keySet()) {
			String value = attributes.get(attribute);
			if (value != null) {
				title += value + "\n";
			}
		}
		if (title.length() < 1) {
			return "";
		}
		
		return title.substring(0, title.length() - 1);
	}

	public abstract String getMeketreUrl();

	public void setPelagiosUrl(String pelagiosUrl) {
		this.pelagiosUrl = pelagiosUrl;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void write(PrintStream out) {
		if (pelagiosUrl != null) {
			out.println("    <oac:Annotation rdf:about=\"http://meketre.org/" + getType() + "#" + getId() + "\">");
			out.println("        <rdf:type rdf:resource=\"http://www.openannotation.org/ns/Annotation/\"/>");
			out.println("        <oac:hasBody rdf:resource=\"" + pelagiosUrl
					+ "\" />");
			out.println("        <oac:hasTarget rdf:resource=\"http://www.meketre.org/repository/"
					+ getType() + "/" + getId() + "\" />");
			out.println("        <dct:title>" + getTitle() + "</dct:title>");
			out.println("    </oac:Annotation>");
			out.println();
		} else {
			System.out.println(this + " has no Pelagios-URL!");
		}
	}

	public String toString() {
		return getType() + "#" + getId();
	}
}
