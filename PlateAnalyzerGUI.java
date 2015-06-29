import javax.swing.*;

public class PlateAnalyzerGUI extends JFrame {
	public PlateAnalyzerGUI() {
		initUI();
	}

    private void initUI() {
        setTitle("FDSS Plate Analyzer");
        setSize(880, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

	public static void main(String[] args) {
		JFrame f = new PlateAnalyzerGUI();
		f.setVisible(true);
	}
}