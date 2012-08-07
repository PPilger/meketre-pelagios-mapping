import java.util.ArrayList;
import java.util.List;

public class Tupel {
	private List<String> attributes = new ArrayList<String>();

	/**
	 * Creates a new tupel with attributes parsed from the input. Format:
	 * attr1,attr2,...,attrN
	 * 
	 * @param tupel
	 */
	public Tupel(String tupel) {
		String[] attArray = tupel.split(",");

		for (int i = 0, j = 1; i < attArray.length; i = j) {
			String newAttribute = attArray[i];

			// If the attribute starts with ' it has to end with '
			// Otherwise there is a ',' in the text that has to be considered
			for (j = i + 1; (newAttribute.startsWith("'") ^ newAttribute
					.endsWith("'")) && j < attArray.length; j++) {
				newAttribute += "," + attArray[j];
			}
			attributes.add(newAttribute);
		}
	}

	/**
	 * Parses all tupels from the input. Format: (tupel1),(tupel2),...
	 * 
	 * @param input
	 *            as specified above
	 * @return List of tupels parsed from the input
	 */
	public static List<Tupel> parseTupels(String input) {
		List<Tupel> tupels = new ArrayList<Tupel>();

		input = input.substring(1, input.length() - 1);
		for (String tupel : input.split("\\),\\(")) {
			tupels.add(new Tupel(tupel));
		}

		return tupels;
	}

	public Integer getIntAttribute(int num) {
		String att = attributes.get(num);
		if (att == null || "NULL".equals(att)) {
			return null;
		}
		return Integer.parseInt(att);
	}

	public String getStringAttribute(int num) {
		String att = attributes.get(num);
		if (att == null || "NULL".equals(att) || att.length() < 2) {
			return null;
		}
		att = att.substring(1, att.length() - 1);
		return att;
	}
}
