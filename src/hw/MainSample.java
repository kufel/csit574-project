 package hw;

/**
 * The Class Main Sample
 */
public class MainSample {
		
	/**
	 * Instantiates a new main.
	 */
	public MainSample() {
		System.out.println("Starting program");
		Program program = new Program();
		
//		int[][] test = new int[][] 
//		{ 
//			{40, 40, 150, 10, 150, 150, 30}, 
//			{150, 100, 150, 150, 150, 150, 150}, 
//			{150, 150, 150, 150, 150, 150, 150}, 
//			{150, 150, 150, 140, 150, 150, 150}, 
//			{150, 40, 40, 40, 150, 250, 150}, 
//			{150, 25, 150, 150, 150, 150, 150}, 
//			{150, 20, 150, 150, 70, 150, 150}
//		};
		
		int[][][] test = null;
		test = program.openFile("PPM/grid-16-16-256.dat", 16, 16);
		program.saveFile(test, "output/grid-16-16-256.ppm", "P3", test.length, test[0].length, 256);
//		program.saveHistogram(test, "output-0/sample.csv", 256);
//		program.saveCompressedFile(test, "output-0/sample.rle");

		System.out.println(" ");
		for (int i = 0; i < test.length; i++) {
			String s = "";
			for (int j = 0; j < test[0].length; j++) {
				s += test[i][j][0] + "\t" + test[i][j][1] + "\t" + test[i][j][1] + "\t\t";					
			}
			System.out.println(s);
		}
		
		System.out.println("\nProgram finished");
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		MainSample main = new MainSample();
	}

}
