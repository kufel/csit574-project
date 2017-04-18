 package hw;

/**
 * The Class Main Lenna
 */
public class MainLenna {
		
	/**
	 * Instantiates a new main.
	 */
	public MainLenna() {
		System.out.println("Starting program");
		Program program = new Program();
				
		int[][][] image = program.openFile("PPM/lennacolor-P6-512-512-255.dat", 512, 512);
		int[][][] key = program.openFile("PPM/google-maps-P6-64-64-256.dat", 64, 64);
		int[][][] test = program.encodeLSB(image, key);
		program.saveFile(key, "output/lenna.ppm", "P3", key.length, key[0].length, 2);
		
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
		MainLenna main = new MainLenna();
	}

}
