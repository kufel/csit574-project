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
				
		int[][][] image = program.openFile("PPM/lennacolor-P6-256-256-256.dat", 256, 256);
		program.saveHistogram(image, "output/lennae-iamge-r.cvs", 0, 256);
		program.saveHistogram(image, "output/lenna-iamge-g.cvs", 1, 256);
		program.saveHistogram(image, "output/lenna-iamge-b.cvs", 2, 256);
		int[][][] key = program.openFile("PPM/browser-64-64-256.dat", 64, 64);
		program.saveHistogram(key, "output/lennae-key-r.cvs", 0, 256);
		program.saveHistogram(key, "output/lenna-key-g.cvs", 1, 256);
		program.saveHistogram(key, "output/lenna-key-b.cvs", 2, 256);
		int[][][] encoded = program.encodeLSB(image, key);
		program.saveHistogram(encoded, "output/lennae-encoded-r.cvs", 0, 256);
		program.saveHistogram(encoded, "output/lenna-encoded-g.cvs", 1, 256);
		program.saveHistogram(encoded, "output/lenna-encoded-b.cvs", 2, 256);
		program.saveFile(encoded, "output/lenna-encoded.ppm", "P3", encoded.length, encoded[0].length, 256);
		
		int[][][] decoded = program.decodeLSB(image, encoded, 64, 64);
		program.saveHistogram(decoded, "output/lennae-decoded-r.cvs", 0, 256);
		program.saveHistogram(decoded, "output/lenna-decoded-g.cvs", 1, 256);
		program.saveHistogram(decoded, "output/lenna-decoded-b.cvs", 2, 256);
		program.saveFile(decoded, "output/lenna-decode.ppm", "P3", decoded.length, decoded[0].length, 256);

//		System.out.println(" ");
//		for (int i = 0; i < image.length; i++) {
//			String s = "";
//			for (int j = 0; j < image[0].length; j++) {
//				s += image[i][j][0] + "\t" + image[i][j][1] + "\t" + image[i][j][1] + "\t\t";					
//			}
//			System.out.println(s);
//		}
		
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
