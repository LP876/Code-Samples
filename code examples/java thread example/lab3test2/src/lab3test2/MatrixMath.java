package lab3test2;
public class MatrixMath extends Thread {
	private static int[][] a;
	private static int[][] b;
	private static int[][] R;
	private int i;
	private int j;
	private int M;
	private int L;
	private int P;

	public MatrixMath(int[][] matrixA, final int[][] matrixB, final int[][] result, int i, int j, int z1) { 
		a = matrixA;
		b = matrixB;
		R = result;
		this.i = i;
		this.j = j;
		this.M = z1; // a[0].length
	}

	public void run() {
		synchronized (R) {
			//individual thread work allocation
			for (L = 0, P = 0; P < M; P++)
				//adding the sums
				L += a[i][P] * b[P][j];
			//allocating the total to an index in the result array
			R[i][j] = L;
		}
	}

	public static int[][] returnR() {
		return R;
	}

	public static int[][] multiply(final int[][] a, final int[][] b) {
		/*is matrix multiplication possible? 
		this checks if the number of columns in matrix A 
		is equal to the number of rows in matrix b*/

		final int axisX = a.length;
		final int axisY = b[0].length;
		final int matrixAcol = a[0].length;
		final int matrixBrow = b.length;

		if (matrixAcol != matrixBrow) {
			System.out.println("Cannnot multiply");
			return null;
		}
		final int[][] r = new int[axisX][axisY];
		int i, j;
		for (i = 0; i < axisX; i++)
			for (j = 0; j < axisY; j++) {
				try {
					MatrixMath tempThread = new MatrixMath(a, b, r, i, j, matrixAcol);
					tempThread.start();
					tempThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		return MatrixMath.returnR();
	}
}