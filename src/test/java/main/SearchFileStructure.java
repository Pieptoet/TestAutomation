package main;

import java.io.File;
import java.io.IOException;

public class SearchFileStructure {

	// Methode om door de mappenstructuur heen te zoeken naar juiste afbeelding die is doorgegeven.
	public static String searchImage(File folder, String filename) throws IOException {

		File[] files = folder.listFiles();
		String rValue = "";

		for (File file : files) {
			if (file.isDirectory()) {
				rValue = searchImage(file, filename);
				if (rValue != "") {
					return rValue;
				}
			}
			if (file.getAbsoluteFile().getName().equals(filename)) {
				rValue = file.getCanonicalPath();
			}
		}
		return rValue;
	}
}
