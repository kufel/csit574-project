 package hw;

/**
 * The Class Main Mandrill
 */
public class MainMandrill {
		
	/**
	 * Instantiates a new main.
	 */
	public MainMandrill() {
		System.out.println("Starting program");
		Program program = new Program();
				
		int[][][] image = program.openFile("PPM/mandrill_P6-_512_512.dat", 512, 512);
		program.saveHistogram(image, "output/mandrill-iamge-r.csv", 0, 256);
		program.saveHistogram(image, "output/mandrill-iamge-g.csv", 1, 256);
		program.saveHistogram(image, "output/mandrill-iamge-b.csv", 2, 256);
		int[][][] key = program.openFile("PPM/msu-P6-150-150-256.dat", 150, 150);
		program.saveHistogram(key, "output/mandrill-key-r.csv", 0, 256);
		program.saveHistogram(key, "output/mandrill-key-g.csv", 1, 256);
		program.saveHistogram(key, "output/mandrill-key-b.csv", 2, 256);
		int[][][] encoded = program.encodeLSB(image, key);
		program.saveHistogram(encoded, "output/mandrill-encoded-r.csv", 0, 256);
		program.saveHistogram(encoded, "output/mandrill-encoded-g.csv", 1, 256);
		program.saveHistogram(encoded, "output/mandrill-encoded-b.csv", 2, 256);
		program.saveFile(encoded, "output/mandrill-encoded.ppm", "P3", encoded.length, encoded[0].length, 256);
		
		int[][][] decoded = program.decodeLSB(image, encoded, 150, 150);
		program.saveHistogram(decoded, "output/mandrill-decoded-r.csv", 0, 256);
		program.saveHistogram(decoded, "output/mandrill-decoded-g.csv", 1, 256);
		program.saveHistogram(decoded, "output/mandrill-decoded-b.csv", 2, 256);
		program.saveFile(decoded, "output/mandrill-decode.ppm", "P3", decoded.length, decoded[0].length, 256);

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
		MainMandrill main = new MainMandrill();
	}

}
