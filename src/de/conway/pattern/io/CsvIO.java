package de.conway.pattern.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvIO {

	public static void writeFile(List<? extends CSVBean> beans, String filename) throws IOException {
		
		FileWriter writer = new FileWriter(filename);

		StringBuilder sb = new StringBuilder();
		
		sb.append(beans.get(0).getCsvHeader());
		sb.append("\n");
		
		for(CSVBean b : beans) {
			
			sb.append(b.getCsvString());
			sb.append("\n");
		}
		
		
		writer.write(sb.toString());
		writer.flush();
		writer.close();
		
	}
	
}
