import java.io.IOException;
import java.io.File;

public class PlateAnalyzer {
	private static final String INPUT_DIR = "test_files/";
	private static final String OUTPUT_DIR = "output/";

	/**
	 * Reads in all the plates in the test_files folder 
	 * and ouputs normalized data to results
	 */
	public static void main(String[] args) {
		File input = new File(INPUT_DIR);
		File[] files = input.listFiles();

	}

	// private static String getNormalized() {
	// 	return;
	// }
}