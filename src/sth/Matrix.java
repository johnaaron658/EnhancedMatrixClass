package sth;


public class Matrix {
	
	/*PRIVATE STUFF*/
	
	private Fraction[][] matrix;
	
	
	
	/*CONSTRUCTORS*/
	
	//Constructor for Fraction singletons
	public Matrix(Fraction x){
		matrix = new Fraction[1][1];
		matrix[0][0] = x;
	}
	
	//Constructor for int singletons
	public Matrix(int x){
		matrix = new Fraction[1][1];
		matrix[0][0] = new Fraction(x);
	}
	
	//Constructor for double singletons
	public Matrix(double x){
		matrix = new Fraction[1][1];
		matrix[0][0] = new Fraction(x);
	}
	
	//Constructor for Fraction arrays
	public Matrix(Fraction[] array){
		matrix = new Fraction[1][array.length];
		matrix[0] = array;
	}
	
	//Constructor for integer arrays
	public Matrix(int[] array){
		matrix = new Fraction[1][array.length];
		for(int j = 0; j < array.length; j++)
			matrix[0][j] = new Fraction(array[j]);
	}
	
	//Constructor for double arrays
	public Matrix(double[] array){
		matrix = new Fraction[1][array.length];
		for(int j = 0; j < array.length; j++)
			matrix[0][j] = new Fraction(array[j]);
	}
	
	//Constructor for 2D Fraction array
	public Matrix(Fraction[][] mat){
		matrix = mat;
	}
	
	//Constructor for 2D integer array
	public Matrix(int[][] mat){
		matrix = new Fraction[mat.length][mat[0].length];
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat[0].length; j++){
				matrix[i][j] = new Fraction(mat[i][j]);
			}
		}
	}
	
	//Constructor for 2D double array (identical to the integer version)
	public Matrix(double[][] mat){
		matrix = new Fraction[mat.length][mat[0].length];
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat[0].length; j++){
				matrix[i][j] = new Fraction(mat[i][j]);
			}
		}
	}
	
	
	
	/*GETTERS*/
	
	//Gets 2D Fraction array
	public Fraction[][] mat(){
		return matrix;
	}

	//Gets i-th matrix row
	public Matrix row(int i){
		return new Matrix(this.mat()[i]);
	}
	
	public Matrix row(){
		return row(0);
	}
	
	//Gets j-th matrix column
	public Matrix column(int j){
		Fraction[][] mat1 = new Fraction[this.mat().length][1];
		for(int i = 0; i < this.mat().length; i++)
			mat1[i][0] = this.mat()[i][j];
		return new Matrix(mat1);
	}
	
	public Matrix column(){
		return column(0);
	}
	
	//To String
	public String toString(){
		String s = new String();
		for(int i = 0; i < this.mat().length; i++){ //loop for each row
			
			for(int j = 0; j < this.mat()[0].length; j++) // loop for each element in the row
				s = s + mat()[i][j] + " ";
			s = s + "\n"; 
		}
		return s;
	}

	//Inner/dot product - this only works for one dimensional ROW matrices
	private Fraction dot(Matrix v){
		if(this.mat()[0].length != v.mat()[0].length){
			System.out.println("Cannot take dot product.");
			return null;
		}
		Fraction sum = new Fraction();
		for(int j = 0; j < this.mat()[0].length; j++)
			sum = sum.plus(this.mat()[0][j].times(v.mat()[0][j]));
		return sum;
	}
	
	//Identity Matrix
	public static Matrix I(int n){
		Fraction[][] mat1 = new Fraction[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i == j)
					mat1[i][j] = new Fraction(1);
				else
					mat1[i][j] = new Fraction(0);
			}		
		}
		return new Matrix(mat1);
	}
	
	
	
	/*MODIFIERS*/
	
	//Deletes i-th row
	public Matrix deleteRow(int i){
		//Create 2D Fraction array with m-1 rows
		Fraction[][] mat1 = new Fraction[this.mat().length-1][this.mat()[0].length]; 
		int i2 = 0;
		
		//This loop traverses through all the rows of the original matrix
		for(int i1 = 0; i1 < this.mat().length; i1++){
			//If i1 equals desired row to be deleted, we do not copy it to the new matrix mat1
			if(i1 == i)
				continue;
			else{
				mat1[i2] = this.mat()[i1];
				i2++;
			}
		}
		return new Matrix(mat1);	
	}

	//Deletes j-th column
	public Matrix deleteColumn(int j){
		//Create 2D Fraction array with n-1 columns
		Fraction[][] mat1 = new Fraction[this.mat().length][this.mat()[0].length-1];
		int j2;
		
		//The outer loop traverses through all the rows of the original matrix
		for(int i = 0; i < this.mat().length; i++){
			j2 = 0;
			
			//This inner loop traverses through each element in the current row i
			for(int j1 = 0; j1 < this.mat()[0].length; j1++){
				//If j1 equals the desired column/element to be deleted, we do not copy it to the new matrix mat1
				if(j1 == j)
					continue;
				else{
					mat1[i][j2] = this.mat()[i][j1];
					j2++;
				}
			}
		}
		return new Matrix(mat1);
	}
	
	//Switch rows
	public Matrix switchRows(int row1, int row2){
		Fraction[] temp;
		temp = matrix[row1];
		matrix[row1] = matrix[row2];
		matrix[row2] = temp;
		return new Matrix(matrix);
	}
	
	//Switch columns
	public Matrix switchCols(int col1,int col2){
		return this.transpose().switchRows(col1,col2).transpose();
	}
	
	//Scale a row
	public Matrix scaleRow(int i,Fraction c){
		matrix[i] = this.row(i).scale(c).mat()[0];
		return new Matrix(matrix);
	}
	
	public Matrix scaleRow(int i,int c){
		return scaleRow(i,new Fraction(c));
	}
	
	public Matrix scaleRow(int i, double c){
		return scaleRow(i,new Fraction(c));
	}
	
	//Scale a column
	public Matrix scaleCol(int j,Fraction c){
		return this.transpose().scaleRow(j, c).transpose();
	}
	
	public Matrix scaleCol(int j,int c){
		return scaleCol(j,new Fraction(c));
	}
	
	public Matrix scaleCol(int j,double c){
		return scaleCol(j,new Fraction(c));
	}
	
	//Add one row to another
	public Matrix addRowToRow(int row1,int row2,Fraction c){
		matrix[row2] = this.row(row2).plus(this.row(row1).scale(c)).mat()[0];
		return new Matrix(matrix);
	}
	
	public Matrix addRowToRow(int row1,int row2,int c){
		return addRowToRow(row1,row2,new Fraction(c));
	}
	
	public Matrix addRowToRow(int row1,int row2,double c){
		return addRowToRow(row1,row2,new Fraction(c));
	}
	
	public Matrix addRowToRow(int row1,int row2){
		return addRowToRow(row1,row2,1);
	}
	
	//Add one column to another
	public Matrix addColToCol(int col1,int col2,Fraction c){
		return this.transpose().addRowToRow(col1,col2,c).transpose();
	}
	
	public Matrix addColToCol(int col1,int col2,int c){
		return addColToCol(col1,col2,new Fraction(c));
	}
	
	public Matrix addColToCol(int col1,int col2,double c){
		return addColToCol(col1,col2,new Fraction(c));
	}
	
	public Matrix addColToCol(int col1,int col2){
		return addColToCol(col1,col2,1);
	}
	
	//Augments a matrix with another matrix
	public Matrix augment(Matrix B){
		//Sufficient condition for augmentation
		if(this.mat().length == B.mat().length){
			//Creates new matrix of combined columns
			Fraction[][] C = new Fraction[this.mat().length][this.mat()[0].length + B.mat()[0].length];
			
			//This outer loop traverses through all the rows of the new matrix C
			for(int i = 0; i < this.mat().length; i++){
				
				//This inner loop adds elements to each row of the new matrix C
				for(int j = 0; j < this.mat()[0].length + B.mat()[0].length;){
					
					//Adds the elements from the current matrix
					for(int j1 = 0; j1 < this.mat()[0].length; j1++,j++)
						C[i][j] = this.mat()[i][j1];
					
					//Adds the elements from the matrix B
					for(int j2 = 0; j2 < B.mat()[0].length; j2++,j++)
						C[i][j] = B.mat()[i][j2];
				}
			}
			return new Matrix(C);
		}
		return null;
	}
	
	
	
	/*COMPARISON*/
	
	//Same Size
	public boolean sameSizeAs(Matrix B){
		if(this.mat().length == B.mat().length &&
		   this.mat()[0].length == B.mat()[0].length)
			return true;
		return false;
	}
	
	//Valid Multiplication
	public boolean canMultiply(Matrix B){
		if(this.mat()[0].length == B.mat().length)
			return true;
		return false;
	}
	
	//Equality
	public boolean isEqualTo(Matrix B){
		//If they don't have the same size, they're obviously not equal
		if(this.sameSizeAs(B) == false)
			return false;
		for(int i = 0; i < this.mat().length; i++){
			for(int j = 0; j < this.mat()[0].length; j++){
				//If one of the elements are not equal, then they're not equal
				if(this.mat()[i][j].isEqualTo(B.mat()[i][j]) == false)
					return false;
			}		
		}
		return true;
	}

	
	
	/*PROPERTIES*/
	
	//Square
	public boolean square(){
		if(this.mat().length == this.mat()[0].length)
			return true;
		return false;
	}
	
	//Diagonal
	public boolean diagonal(){
		if(this.square() == false)
			return false;
		int order = this.mat().length;
		for(int i = 0; i < order; i++)
			for(int j = 0; j < order; j++)
				//if one of the diagonal entries is zero or if one of the non-diagonal entries is non-zero
				if( (i == j && this.mat()[i][j].isEqualTo(new Fraction(0)) )||
						(i != j && !this.mat()[i][j].isEqualTo(new Fraction(0)) ) ) 
						return false;
		return true;
	}
	
	//Determinant
	public Fraction det(){
		if(this.square() == false)
			return new Fraction();
		if(this.mat()[0].length == 1)
			return matrix[0][0];
		if(this.mat().length == 2){
			return matrix[0][0].times(matrix[1][1]).minus(matrix[0][1].times(matrix[1][0]));
		}
		if(this.mat().length == 3){	
			Fraction x = matrix[0][0].times(matrix[1][1]).times(matrix[2][2]).plus(matrix[0][1].times(matrix[1][2]).times(matrix[2][0])).plus(matrix[0][2].times(matrix[1][0]).times(matrix[2][1]));
			Fraction y = matrix[2][0].times(matrix[1][1]).times(matrix[0][2]).plus(matrix[2][1].times(matrix[1][2]).times(matrix[0][0])).plus(matrix[2][2].times(matrix[1][0]).times(matrix[0][1]));
			return x.minus(y);
		}
		else{
			Fraction a = new Fraction(-1);
			Fraction sum = new Fraction();
			for(int j = 0; j < this.mat().length; j++)
				sum = sum.plus(a.raisedTo(j).times(matrix[0][j].times(this.deleteRow(0).deleteColumn(j).det())));
			return sum;
		}
	}
	
	//Row Echelon Form (REF)
	private Matrix ref(){
			Fraction zero = new Fraction();
			Fraction one = new Fraction(1);
			Fraction neg = new Fraction(-1);
			int n = matrix.length;

			
			Matrix A = this;
			//The k-loop controls the elimination process
			for(int k = 0; k < n - 1; k++){
				//If k-th entry is zero
				if(A.mat()[k][k].isEqualTo(zero)){
	
					int l;
					//look for row with no zero k-th entry
					for(l = k+1; l < n; l++){
						//If such a row is found, switch the rows and stop the loop
						if(A.mat()[l][k].isEqualTo(zero) == false){
							A = A.switchRows(k,l);
							l = n;
						}
					}
					//If no such row is found
					if(l == n){
						A = A.switchRows(k,n-1);
						continue;
					}
						
				}		
				//If k-th entry is not yet equal to one
				if(A.mat()[k][k].isEqualTo(one) == false)
					A = A.scaleRow(k,mat()[k][k].inverse());
				
				//Elimination Process
				for(int i = k+1; i < n; i++)
					A = A.addRowToRow(k, i,A.mat()[i][k].times(neg));
				
			}
			//If last row contains zero
			if(A.mat()[n-1][n-1].isEqualTo(zero) == true)
				return A;
			//If last row isn't equal to one yet
			if(A.mat()[n-1][n-1].isEqualTo(one) == false)
				A = A.scaleRow(n-1, A.mat()[n-1][n-1].inverse());
			return A;
		}

	//Reduced Row Echelon Form (RREF)
	private Matrix rref(){
		Matrix A = this;
		Fraction neg = new Fraction(-1);
		int n = matrix.length; //number of rows
		A = A.ref();
		
		//k - loop controls the elimination process
		for(int k = 1; k < n; k++){
			//i - loop: Elimination process
			for(int i = k-1; i >= 0; i--){
				A = A.addRowToRow(k,i,A.mat()[i][k].times(neg));
			}
		}
		return A;
	}
	
	
		
	/*OPERATIONS*/
	
	//Transpose
	public Matrix transpose(){
		Fraction[][] C = new Fraction[this.mat()[0].length][this.mat().length];
		for(int i = 0; i < this.mat()[0].length; i++)
			for(int j = 0; j < this.mat().length; j++)
				C[i][j] = this.mat()[j][i];

		return new Matrix(C);	
	}
	
	//Scalar multiplication: for Fraction, integer, and double constants
	public Matrix scale(Fraction c){
		Fraction[][] mat1 = new Fraction[this.mat().length][this.mat()[0].length];
		for(int i = 0; i < this.mat().length; i++)
			for(int j = 0; j < this.mat()[0].length; j++)
				mat1[i][j] = this.mat()[i][j].times(c);
		return new Matrix(mat1);
	}
	
	
	public Matrix scale(int c){
		return scale(new Fraction(c));
	}
	
	
	public Matrix scale(double c){
		return scale(new Fraction(c));
	}

	//Addition
	public Matrix plus(Matrix B){
		if(this.sameSizeAs(B) == false){
			System.out.println("Addition invalid. Not the same size.");
			return null;
		}
		Fraction[][] mat1 = new Fraction[this.mat().length][this.mat()[0].length];
		for(int i = 0; i < this.mat().length; i++)
			for(int j = 0; j < this.mat()[0].length; j++)
				mat1[i][j] = this.mat()[i][j].plus(B.mat()[i][j]);
		return new Matrix(mat1);
	}

	//Subtraction
	public Matrix minus(Matrix B){
		return this.plus(B.scale(-1));
	}

	//Multiplication
	public Matrix times(Matrix B){
		if(this.canMultiply(B) == false){
			System.out.println("Invalid Multiplication!");
			return null;
		}
		Fraction[][] mat1 = new Fraction[this.mat().length][B.mat()[0].length];
		for(int i = 0; i < this.mat().length; i++)
			for(int j = 0; j < B.mat()[0].length; j++)
				mat1[i][j] = this.row(i).dot(B.transpose().row(j));
		
		return new Matrix(mat1);
	}
	
	//Inverse
	public Matrix inverse(){
		Fraction zero = new Fraction(0);
		int n = matrix.length;
		if(this.det().isEqualTo(zero)){
			System.out.println("Matrix is not invertible.");
			return null;
		}
		Matrix A = this.augment(I(n)).rref();
		for(int i = 0; i < n; i++)
			A = A.deleteColumn(0);
		return A;
	}

	
}
