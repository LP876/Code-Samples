//program to generate matrices and operate on them using threads
package lab3test2;

class Lab3 { 

	//third number is the range of numbers you would like to see in the Matrix
	public static int [][] a= MatrixMaker.buildMatrix(3,3,10);
	public static int [][] b= MatrixMaker.buildMatrix(3,4,10);
	public static void main(String [] args) { 
		int [][] x = MatrixMath.multiply(a, b);
		System.out.println("Matrix A ");
		MatrixMaker.print(a);
		System.out.println("");
		System.out.println("Matrix B ");
		MatrixMaker.print(b);
		System.out.println("");
		System.out.println("Result");
		MatrixMaker.print(x);
		
	}
}