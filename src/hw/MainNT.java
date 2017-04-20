 package hw;

/**
 * The Class Main Lenna
 */
public class MainNT {
		
	/**
	 * Instantiates a new main.
	 */
	public MainNT() {
		System.out.println("Starting program");
		Program program = new Program();
				
		int[][][] image = program.openFile("PPM/NT-P6-1152-864-255.dat", 864, 1152);
		int[][][] key = program.openFile("PPM/minion-P6-300-300-256.dat", 300, 300);
		int[][][] encoded = program.encodeLSB(image, key);
		program.saveFile(encoded, "output/nt-encoded.ppm", "P3", encoded.length, encoded[0].length, 256);
		
		int[][][] decoded = program.decodeLSB(image, encoded, 300, 300);
		program.saveFile(decoded, "output/nt-decode.ppm", "P3", decoded.length, decoded[0].length, 256);

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
		MainNT main = new MainNT();
	}

}
