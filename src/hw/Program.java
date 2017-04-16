package hw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Class Program
 * A collection of all image editing functions.
 * 
 * @author Sebastian Kufel
 * @version 2
 * @since 02/01/2017
 */
public class Program {
		
	/**
	 * Open file.
	 *
	 * @param file the file
	 * @param width the width
	 * @param height the height
	 * @return the int[][][]
	 */
	public int[][][] openFile(String file, int width, int height) {
		FileInputStream in = null;

		int[][][] matrix = new int[height][width][3];

		try {
			in = new FileInputStream(file);

			// Save the file line by line
			int c;
			int x = 0;
			int y = 0;
			int z = 0;
			while ((c = in.read()) != -1) {
				// out.write(c);
				matrix[y][x][z] = c;
				System.out.println(c + " [" + y + "," + x + "," + z + "]");
				
				if (z <= 1) {
					z++;
				} else {
					z = 0;
					if (x < width  - 1) {
						x++;
					} else {
						x = 0;
						y++;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return matrix;
	}
	
	/**
	 * Save file.
	 *
	 * @param matrix the matrix
	 * @param file the file
	 * @param type the type
	 * @param width the width
	 * @param height the height
	 * @param grayLevel the gray level
	 */
	public void saveFile(int[][][] matrix, String file, String type, int width, int height, int alphaLevel) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			// Append 3 lines to the output file
			String str = null;
			str = type + "\n";
			out.write(str.getBytes());
			str = width + "\t" + height + "\n";
			out.write(str.getBytes());
			str = (alphaLevel - 1) + "\n";
			out.write(str.getBytes());

			// Output matrix into a file line by line
			int ySize = matrix.length;
			int xSize = matrix[1].length;
			for (int i = 0; i < ySize; i++) {
				String s = "";
				for (int j = 0; j < xSize; j++) {
					s += matrix[i][j][0] + "\t" + matrix[i][j][1] + "\t" + matrix[i][j][2] + "\t\t";
				}
				s += "\n";
				// System.out.print(s);
				out.write(s.getBytes());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Image " + file + " saved.");
	}
}
