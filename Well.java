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
	Double netSignal;
	Double interval = 1.0;
	Double[] data;

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
			this.data = data;
		}
	}

	/**
	 *
	 */
	public Double getNetSignal(Double addTime) {
		Double[] maxs = getMax(addTime);
		return maxs[1] - maxs[0];
	}


	public double getMax(int start, int end) {
		int startIndex = (int) Math.floor(start/interval);
		int endIndex = (int) Math.floor(end/interval);
		double curr = 1 << 32;
		for (; startIndex < endIndex; startIndex++) {
			if (data[startIndex] > curr) {
				curr = data[startIndex];
			}
		}
		return curr;
	}
	/**
	 *
	 */
	public Double[] getMax(Double addTime) {
		Double[] max = new Double[] { 0d, 0d };
		Double holder;
		int index = 0;
		for (Double time : ts.keySet()) {
			if (time > addTime) {
				index = 1; 
			}
			holder = ts.get(time);
			if (max[index] < holder) {
				max[index] = holder;
			}
		}
		return max;
	}
}