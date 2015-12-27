package sth;

public class sth {
	public static void main(String args[]){
		int[][] mat1 = {{5,2,0,0,-2},
						{0,1,4,3,2},
						{0,0,2,6,3},
						{0,0,3,4,1},
						{0,0,0,0,2}};
		int[][] mat2 = {{1,-2,3,9},
						{-1,3,0,-4},
						{2,-5,5,17}};
		//int[][] mat3 = {{-1,0},{3,1},{2,4}};
		//int[] mat2 = {1,4,3};
		Matrix A = new Matrix(mat2);
		//Matrix B = new Matrix(mat2);
		//Matrix C = new Matrix(mat3);
		
		System.out.println(A.REF().toString());
		
	}
}
