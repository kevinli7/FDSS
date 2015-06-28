import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.ArrayList;

/**
 * A class that represents a 384 well or 1536 well plate and holds 
 * information on the plates in Well objects.  
 * Provides utility methods that help PlateAnalyzer to generate data.
 *
 * @author Kevin Li
 */
public class Plate {
	Well[][] wells;


	/* matches either unix/mac or windows line separators */
    private static final String DELIMITER = "	";

	/**
	 * Constructor for the Plate Class
	 * Does most of the heavy lifting for the class itself by initalizing data
	 *
	 * @param filename is name of the raw data .txt file
	 */
	public Plate(String filename) {
		List<String> rawData;
		try {
			rawData = Files.readAllLines(Paths.get(filename));
		} catch (IOException e) {
			System.out.println(String.format("Error with %1s", filename));
			System.out.println(e);
			return;
		}

		if (rawData.size() > 500) {
			wells = new Well[48][32];
		} else {
			wells = new Well[24][16];
		}
		int index = getTimeIndex(rawData);
		String[] tempTime = rawData.get(index).split(DELIMITER);
		Double[] time = new Double[tempTime.length - 4];
		for (int i = 4; i < tempTime.length; i++) {
			time[i-4] = Double.parseDouble(tempTime[i]) / 1000d;
		}
		index += 1;
		int row = 0;
		int column = 0;
		for (; index < rawData.size(); index++) {
			wells[column][row] = new Well(time, extractData(rawData.get(index)));
			if (++column == wells.length) {
				column = 0;
				row += 1;
			}
			if (row == wells[0].length) {
				break;
			}
		}
	}

	/**
	 * Takes the line that represents the data of a well
	 * and converts it into a Double[]
	 *
	 * @param well is a String that is the line of txt that represents a well's data
	 * @return a double array with all the values for that well
	 */
	private Double[] extractData(String well) {
		String[] data = well.split(DELIMITER);
		Double[] d = new Double[data.length-4];
		for (int i = 4; i < data.length; i++) {
			d[i-4] = Double.parseDouble(data[i]);
		}
		return d;
	}

	/**
	 * Runs through the raw data to find the index of time values line
	 *
	 * @param rawData is txt file that is broken into lines
	 * @return the int value of the index of the time line
	 */
	private int getTimeIndex(List<String> rawData) {
		String time = "	\"No.\"";
		for (int i = 0; i < rawData.size(); i += 1) {
			String temp = rawData.get(i);
			if (temp.length() > 7 && temp.substring(0,6).equals(time)) {
				return i;
			}
		}
		System.out.println("there was an error");
		return -1;
	}

	/**
	 * A simple testing suite for the Plate class
	 *
	 * @param args has no intended purpose
	 */
	public static void main(String[] args) {
		String filename = "test_files/062215_h1.txt";
		Plate test = new Plate(filename);
		Well t = test.wells[7][3];
		// for (Double time : t.ts.keySet()) {
		// 	System.out.println(String.format("%1f, %2f", time, t.ts.get(time)));
		// }
	}
}