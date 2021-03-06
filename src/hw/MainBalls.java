 package hw;

/**
 * The Class Main Balls
 */
public class MainBalls {
		
	/**
	 * Instantiates a new main.
	 */
	public MainBalls() {
		System.out.println("Starting program");
		Program program = new Program();
				
		int[][][] image = program.openFile("PPM/balls-P6_512-512.dat", 512, 512);
		program.saveHistogram(image, "output/balls-iamge-r.csv", 0, 256);
		program.saveHistogram(image, "output/balls-iamge-g.csv", 1, 256);
		program.saveHistogram(image, "output/balls-iamge-b.csv", 2, 256);
		int[][][] key = program.openFile("PPM/google-P6-200-200-256.dat", 150, 150);
		program.saveHistogram(key, "output/balls-key-r.csv", 0, 256);
		program.saveHistogram(key, "output/balls-key-g.csv", 1, 256);
		program.saveHistogram(key, "output/balls-key-b.csv", 2, 256);
		int[][][] encoded = program.encodeLSB(image, key);
		program.saveHistogram(encoded, "output/balls-encoded-r.csv", 0, 256);
		program.saveHistogram(encoded, "output/balls-encoded-g.csv", 1, 256);
		program.saveHistogram(encoded, "output/balls-encoded-b.csv", 2, 256);
		program.saveFile(encoded, "output/balls-encoded.ppm", "P3", encoded.length, encoded[0].length, 256);
		
		int[][][] decoded = program.decodeLSB(image, encoded, 150, 150);
		program.saveHistogram(decoded, "output/balls-decoded-r.csv", 0, 256);
		program.saveHistogram(decoded, "output/balls-decoded-g.csv", 1, 256);
		program.saveHistogram(decoded, "output/balls-decoded-b.csv", 2, 256);
		program.saveFile(decoded, "output/balls-decode.ppm", "P3", decoded.length, decoded[0].length, 256);

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
		MainBalls main = new MainBalls();
	}

}
