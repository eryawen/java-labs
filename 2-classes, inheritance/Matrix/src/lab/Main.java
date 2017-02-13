package lab;

public class Main {
	
	public static void main(String[] args) {
		Matrix matrix = new Matrix();
		matrix.eye(5);
		matrix.show();
		
		Matrix m = Matrix.add(matrix, matrix);
		m.show();
		
		Matrix n = Matrix.mul(m, m);
		n.show();
		
		System.out.println(n.det());
	}
}
