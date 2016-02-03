import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CreateCsv {

	/**
	 * @param args
	 */
	
	static Map <String, ArrayList<Integer>> mainDict;
	
	static int count = 26;
	
	public static void main(String[] args) {
		mainDict = new HashMap<>();
		try {
		for (int i = 1; i <= count; i++) 			
				parse(i, getFileName(i));
		
		saveResults();
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	static void parse(int fileNumber, String fileName) throws IOException {
		FileInputStream fstream;		
		fstream = new FileInputStream(fileName);		
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine = br.readLine();

	
		while ((strLine = br.readLine()) != null)   {
			String [] line = strLine.split(";");
			for (int j = 0; j <= 1; j++) {
				line[j] = line[j].replace(' ', '_');
				line[j] = line[j].toLowerCase();
				
				if (!mainDict.containsKey(line[j]))
					init(line[j]);
				
				put(line[j], fileNumber - 1, line[j + 2]);
				
			}		
		}
		
		br.close();
		fstream.close();
	}
	
	static String getFileName (Integer fileNumber) {
		return "graphs/" + fileNumber.toString() + ".txt";
	}
	
	static void put(String key, Integer num, String value) {
		mainDict.get(key).set(num, Integer.decode(value));
	}
	
	static void init(String name) {
		mainDict.put(name, new ArrayList<Integer>());
		for (int i = 0; i < count; i++)
			mainDict.get(name).add(i, -1);
		
	}
	
	static void saveResults() throws IOException {
		FileWriter fw = new FileWriter ("result.csv");
		
		for (String key : mainDict.keySet()) {
			fw.append(key + ',');
			for (Integer i : mainDict.get(key)) {
				fw.append(i.toString() + ',');
			}
			fw.append('\n');
		}
		
		fw.close();
	}

}
