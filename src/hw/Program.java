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

import com.sun.org.apache.xml.internal.security.keys.content.KeyValue;

/**
 * The Class Program
 * A collection of all image editing functions.
 * 
 * @author Sebastian Kufel
 * @author Ben Cooper
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
				//System.out.println(c + " [" + y + "," + x + "," + z + "]");
				
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
			int xSize = matrix[0].length;
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
	
	/**
	 * Encode LSB.
	 *
	 * @param matrixA the matrix A
	 * @param matrixB the matrix B
	 * @return the int[][][]
	 */
	public int[][][] encodeLSB(int[][][] matrixA, int[][][] matrixB) {
		int[][][] result = new int[matrixA.length][matrixA[0].length][3];
		int[][][] key = new int[matrixA.length][matrixA[0].length][3];
		
		int yOffset = 0;
		int xOffset = 0;

		// y, x, z
		for (int y = 0; y < matrixB.length; y++) {
			for (int x = 0; x < matrixB[0].length; x++) {
				int orgValR = matrixB[y][x][0];
				int orgValG = matrixB[y][x][1];
				int orgValB = matrixB[y][x][2];
//				System.out.println("original value: " + orgValR + " orgValG:" + orgValG + " orgValB:" + orgValB);
				
				String binaryStringR = Integer.toBinaryString(orgValR);
				while (binaryStringR.length() < 8) {
					binaryStringR = "0" + binaryStringR;
				}
				
				String binaryStringG = Integer.toBinaryString(orgValG);
				while (binaryStringG.length() < 8) {
					binaryStringG = "0" + binaryStringG;
				}
				
				String binaryStringB = Integer.toBinaryString(orgValB);
				while (binaryStringB.length() < 8) {
					binaryStringB = "0" + binaryStringB;
				}

				for (int i = 0; i < binaryStringR.length(); i++) {
//					System.out.println("i:" + i + " y:" + y + " x:" + x + " z:" + 0 + " yOffset:" + yOffset + " xOffset:" + xOffset + " binary:" + binaryStringR);
					key[yOffset][xOffset*8 + i][0] = Integer.parseInt(binaryStringR.substring(i, i + 1));
//					System.out.println("i:" + i + " y:" + y + " x:" + x + " z:" + 0 + " = " + key[yOffset][xOffset][0]);
				}
				
				for (int i = 0; i < binaryStringG.length(); i++) {
//					System.out.println("i:" + i + " y:" + y + " x:" + x + " z:" + 0 + " yOffset:" + yOffset + " xOffset:" + xOffset + " binary:" + binaryStringG);
					key[yOffset][xOffset*8 + i][1] = Integer.parseInt(binaryStringG.substring(i, i + 1));
//					System.out.println("i:" + i + " y:" + y + " x:" + x + " z:" + 0 + " = " + key[yOffset][xOffset][0]);
				}
				
				for (int i = 0; i < binaryStringB.length(); i++) {
//					System.out.println("i:" + i + " y:" + y + " x:" + x + " z:" + 0 + " yOffset:" + yOffset + " xOffset:" + xOffset + " binary:" + binaryStringB);
					key[yOffset][xOffset*8 + i][2] = Integer.parseInt(binaryStringB.substring(i, i + 1));
					//System.out.println("i:" + i + " y:" + y + " x:" + x + " z:" + 0 + " = " + key[yOffset][xOffset][0]);
				}

				if (xOffset * 8 < matrixA[0].length - 8) {
					xOffset++;
				} else {
					xOffset = 0;
					yOffset++;
				}
			}
		}
		
		for (int y = 0; y < result.length; y++) {
			for (int x = 0; x < result[0].length; x++) {
				for (int z = 0; z < 3; z++) {
					int value = matrixA[y][x][z];
					if (value == 255) {
						value -= key[y][x][z];
					} else {
						value += key[y][x][z];
					}
					result[y][x][z] = value;
				}
			}
		}

		return result;
	}
	
	/**
	 * Decode LSB.
	 *
	 * @param matrixA the matrix A
	 * @param matrixB the matrix B
	 * @return the int[][][]
	 */
	public int[][][] decodeLSB(int[][][] matrixA, int[][][] matrixB, int keyX, int keyY) {
		int[][][] result = new int[keyY][keyX][3];
		int[][][] key = new int[matrixA.length][matrixA[0].length][3];
		
		// y, x, z
		for (int y = 0; y < matrixA.length; y++) {
			for (int x = 0; x < matrixA[0].length; x++) {
				for (int z = 0; z < 3; z++) {
					int originalValue = matrixA[y][x][z];
					int keyValue = matrixB[y][x][z];
					
					if (originalValue == 255 && keyValue == 254) {
						key[y][x][z] = 1;
					} else if (originalValue - keyValue == -1) {
						key[y][x][z] = 1;
					} else {
						key[y][x][z] = 0;
					}

				}
			}
		}
		
		int yOffset = 0;
		int xOffset = 0;
		
		// y, x, z
		for (int y = 0; y < key.length; y++) {
			for (int x = 0; x < key[0].length; x += 8) {
				String r = "";
				String g = "";
				String b = "";
				
				for (int i = 0; i < 8; i++) {
					int value = key[y][x + i][0];
					r += value + "";
				}
				
				for (int i = 0; i < 8; i++) {
					int value = key[y][x + i][1];
					g += value + "";
				}
				
				for (int i = 0; i < 8; i++) {
					int value = key[y][x + i][2];
					b += value + "";
				}
				
				int valueR = Integer.parseInt(r, 2);
				int valueG = Integer.parseInt(g, 2);
				int valueB = Integer.parseInt(b, 2);
				
				if (yOffset < keyY) {
//					System.out.print(r + " " + g + " " + b + " ");
//					System.out.print(valueR + " " + valueG + " " + valueB + " ");
//					System.out.println(" yOffset:" + yOffset + " xOffset:" + xOffset + " y:" + y + " x:" + x + " R:" + valueR + " G:" + valueG + " B:" + valueB);
					
					result[yOffset][xOffset][0] = valueR;
					result[yOffset][xOffset][1] = valueG;
					result[yOffset][xOffset][2] = valueB;
				}

				//if (x < (keyX - 1) * 8) {
				if (xOffset < keyX-1) {
					xOffset++;
				} else {
					xOffset = 0;
					yOffset++;
				}
			}
			//xOffset = 0;
		}

		return result;
	}
	
	public void saveHistogram(int[][][] matrix, String file, int zLevel, int alphaLevel) {
		int[] count = new int[alphaLevel];
		int ySize = matrix.length;
		int xSize = matrix[1].length;

		FileOutputStream out = null;
		
		try {
			out = new FileOutputStream(file);
			for (int i = 0; i < ySize; i++) {
				for (int j = 0; j < xSize; j++) {
					//System.out.println("histogram " + i + "," + j);
					count[matrix[i][j][zLevel]] += 1;
				}
			}

			String s = "";
			out.write(s.getBytes());
			for (int i = 0; i < count.length; i++) {
				s = count[i] + "\n";
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
		System.out.println("Histogram " + file + " saved.");
	}
}
