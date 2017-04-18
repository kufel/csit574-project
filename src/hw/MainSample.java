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
		
		int[][][] mask = new int[][][] { { { 255, 0, 219 }, { 30, 125, 0 }, { 74, 60, 86 }, { 8647, 169 } } };
		
		int[][][] image = program.openFile("PPM/grid-16-16-256.dat", 16, 16);
		int[][][] key = program.openFile("PPM/2x2.dat", 2, 2);
		int[][][] encoded = program.encodeLSB(image, key);
		program.saveFile(encoded, "output/encode.ppm", "P3", encoded.length, encoded[0].length, 256);
		
		//int[][][] image2 = program.openFile("PPM/grid-16-16-256.dat", 16, 16);
		int[][][] decoded = program.decodeLSB(image, encoded, 2, 2);
		program.saveFile(decoded, "output/decode.ppm", "P3", decoded.length, decoded[0].length, 256);
		
		System.out.println(" ");
		for (int i = 0; i < encoded.length; i++) {
			String s = "";
			for (int j = 0; j < encoded[0].length; j++) {
				s += encoded[i][j][0] + "\t" + encoded[i][j][1] + "\t" + encoded[i][j][1] + "\t\t";					
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
