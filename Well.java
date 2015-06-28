import java.util.TreeMap;


/**
 * A class that represents a well in an FDSS assay playe
 * either 384 or 1536.  
 * Contains a time series with the raw data for that well
 *
 * @author Kevin Li
 */
public class Well {
	TreeMap<Double, Double> ts = new TreeMap<Double, Double>();

	/**
	 * Constructor for a well.
	 * Assumes input for time and data are the same length
	 * 
	 * @param time is the array of times for the time series of thew ell
	 * @param data is the values associated with each time value
	 */
	public Well(Double[] time, Double[] data) {
		for (int i = 0; i < time.length; i++) {
			ts.put(time[i], data[i]);
		}
	}

}