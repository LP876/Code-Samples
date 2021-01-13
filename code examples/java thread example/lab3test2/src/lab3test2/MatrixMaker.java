package lab3test2;

import java.util.Random;

public class MatrixMaker {

	public static int[][] buildMatrix(int rows, int columns, int range) {
		int[][] result = new int[rows][columns];
		Random random = new Random();

		// adding values at each index.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				result[i][j] = random.nextInt(range)+1;

			}
		}
		return result;
	}
	// to print the matrix
	public static void print(int[][] matrix) {

		System.out.println();

		int rows = matrix.length;
		int columns = matrix[0].length;

		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				System.out.print(matrix[x][y] + "  ");
			}
			System.out.println();
		}

	}

}
