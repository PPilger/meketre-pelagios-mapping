package org.meketre.pelagios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Meketre {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Invalid parameters!\n" + "Syntax:\n"
					+ "java meketre <result rdf-file> <mapping file>");
			return;
		}

		// check, if the input-files exist
		if (!new File(args[1]).exists()) {
			System.out.println("File \"" + args[1] + "\" does not exist!");
			return;
		}

		String resourceFolder = "src" + File.separator + "main"
				+ File.separator + "resources";
		String[] dumpFiles = {
				resourceFolder + File.separator
						+ "meketre_application_Term.sql",
				resourceFolder + File.separator
						+ "meketre_application_Fragment.sql",
				resourceFolder + File.separator
						+ "meketre_application_Tomb.sql",
				resourceFolder + File.separator
						+ "meketre_application_Theme.sql" };
		
		for (String dumpFile : dumpFiles) {
			if (!new File(dumpFile).exists()) {
				System.out.println("File \"" + dumpFile + "\" does not exist!");
				return;
			}
		}

		List<Item> items = loadData(dumpFiles[0], dumpFiles[1], dumpFiles[2],
				dumpFiles[3]);

		Map<String, String> mapping = getMapping(args[1]);

		for (Item item : items) {
			String meketreUrl = item.getMeketreUrl();
			if (meketreUrl != null) {
				item.setPelagiosUrl(mapping.get(meketreUrl + ".html"));
			}
		}

		writeRDF(args[0], items);
	}

	private static String readSQLDump(String filename) {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(filename));

			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("INSERT INTO")) {
					int start = line.indexOf("VALUES", 11) + 7;
					String data = line.substring(start, line.length() - 1);
					if (data.length() < 2) {
						return "()";
					}
					return data;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return "()";
	}

	private static List<Item> loadData(String termFile, String fragFile,
			String tombFile, String themeFile) {
		List<Item> items = new ArrayList<Item>();

		// load texts and urls
		TermMap terms = TermMap.getInstance();
		String termData = readSQLDump(termFile);
		for (Tupel tupel : Tupel.parseTupels(termData)) {
			terms.putTerm(tupel);
		}

		// load fragments
		String fragData = readSQLDump(fragFile);
		for (Tupel tupel : Tupel.parseTupels(fragData)) {
			items.add(new Fragment(tupel));
		}

		// load tombs
		String tombData = readSQLDump(tombFile);
		for (Tupel tupel : Tupel.parseTupels(tombData)) {
			items.add(new Tomb(tupel));
		}

		// load themes
		List<Theme> themes = new ArrayList<Theme>();
		String themeData = readSQLDump(themeFile);
		for (Tupel tupel : Tupel.parseTupels(themeData)) {
			Theme theme = new Theme(tupel, items);
			themes.add(theme);
		}
		items.addAll(themes);

		return items;
	}

	/**
	 * Loads the Meketre- to Pelagios-mapping and fills the pelagiosUrl of all
	 * Items.
	 */
	private static Map<String, String> getMapping(String filename) {
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader(filename));

			// read headline
			in.readLine();

			// load the mapping of Meketre- and Pelagio-places
			Map<String, String> mapping = new TreeMap<String, String>();
			String line;
			while ((line = in.readLine()) != null) {
				Scanner scanner = new Scanner(line);
				scanner.useDelimiter(",");
				String meketreUrl = scanner.next();
				scanner.next();
				String pelagiosUrl = scanner.next();

				if (pelagiosUrl.startsWith("http://")) {
					mapping.put(meketreUrl, pelagiosUrl);
				}
			}

			return mapping;

		} catch (IOException ex) {
			ex.printStackTrace();

			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void writeRDF(String filename, List<Item> items) {
		PrintStream out = null;

		try {
			out = new PrintStream(filename);

			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"");
			out.println("    xmlns:dct=\"http://purl.org/dc/terms/\"");
			out.println("    xmlns:tei=\"http://www.tei-c.org/ns/1.0\"");
			out.println("    xmlns:saws=\"http://www.purl.org/saws/ontology#\"");
			out.println("    xmlns:oac=\"http://www.openannotation.org/ns/\">);");
			out.println();

			for (Item item : items) {
				item.write(out);
			}

			out.println("</rdf:RDF>");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}