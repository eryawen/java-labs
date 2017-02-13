package lab;

public class Matrix {
	double[][] array;
	private int rows;
	private int cols;
	
	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		array = new double[rows][cols];
	}
	
	public Matrix() {
		
	}
	
	public static Matrix add(Matrix first, Matrix second) {
		if (first.rows != second.rows || first.cols != second.cols) {
			throw new IllegalArgumentException("Matrixes have different dimensions");
		} else {
			Matrix m = new Matrix(first.rows, first.cols);
			for (int i = 0; i < m.rows; i++) {
				for (int j = 0; j < m.cols; j++) {
					m.array[i][j] = first.array[i][j] + second.array[i][j];
				}
			}
			return m;
		}
	}
	
	public static Matrix mul(Matrix first, Matrix second) {
		if (first.cols != second.rows) {
			throw new IllegalArgumentException("Matrixes have bad dimensions");
		} else {
			Matrix m = new Matrix(first.rows, first.cols);
			for (int i = 0; i < m.rows; i++) {
				for (int j = 0; j < m.cols; j++) {
					double sum = 0;
					for (int k = 0; k < m.rows; k++) {
						sum += first.array[i][k] * second.array[k][j];
					}
					m.array[i][j] = sum;
				}
			}
			return m;
		}
	}
	
	public void eye(int dim) {
		rows = dim;
		cols = dim;
		array = new double[rows][cols];
		for (int i = 0; i < dim; i++) {
			array[i][i] = 1;
		}
	}
	
	public void show() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println("\n");
		}
		System.out.println("\n");
	}
	
	public double det() {
		if (rows != cols) {
			throw new IllegalArgumentException("Matrix must be square");
		}
		int dim = rows;
		
		if (dim == 1) {
			return array[0][0];
		} else {
			double sum = 0;
			Matrix m = new Matrix(dim - 1, dim - 1);
			for (int i = 0; i < dim; i++) {
				int index = 0;
				for (int j = 0; j < dim; j++) {
					if (j == i) {
						continue;
					} else {
						for (int k = 0; k < dim - 1; k++) {
							m.array[k][index] = this.array[k + 1][j];
						}
						index++;
					}
				}
				sum += Math.pow(-1, 1 + i) * array[0][i] * m.det();
			}
			return sum;
		}
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
}
