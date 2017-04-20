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
		
		//int[][][] mask = new int[][][] { { { 255, 0, 219 }, { 30, 125, 0 }, { 74, 60, 86 }, { 8647, 169 } } };
		
		//int[][][] image = program.openFile("PPM/grid-16-16-256.dat", 16, 16);
		int[][][] image = program.openFile("PPM/browser-64-64-256.dat", 64, 64);
		int[][][] key = program.openFile("PPM/4x4.dat", 4, 4);
		int[][][] encoded = program.encodeLSB(image, key);
		program.saveFile(encoded, "output/sample-encode.ppm", "P3", encoded.length, encoded[0].length, 256);
		
		//int[][][] image2 = program.openFile("PPM/grid-16-16-256.dat", 16, 16);
		int[][][] decoded = program.decodeLSB(image, encoded, 4, 4);
		program.saveFile(decoded, "output/sample-decode.ppm", "P3", decoded.length, decoded[0].length, 256);
		
//		System.out.println(" ");
//		for (int i = 0; i < decoded.length; i++) {
//			String s = "";
//			for (int j = 0; j < decoded[0].length; j++) {
//				s += decoded[i][j][0] + "\t" + decoded[i][j][1] + "\t" + decoded[i][j][1] + "\t\t";					
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
		MainSample main = new MainSample();
	}

}
