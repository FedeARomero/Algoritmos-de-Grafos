package paquete;

import java.util.Arrays;

public class MatrizSimetrica {

	private Integer[] vector;
	private int n;
	
	public MatrizSimetrica() {
		// TODO Auto-generated constructor stub
	}
	
	public MatrizSimetrica(int n) {
		this.vector = new Integer[(n*n-n)/2];
		this.n = n;
	}
	
	public void setFC(int f, int c, int valor) {
		if( f == c )
			return;
		
		int i;
		
		if( f > c ) {
			i = f; f = c; c = i;
		}
		
		i = f*this.n + c - (f*f+3*f+2)/2;
		this.vector[i] = valor;
	}
	
	public Integer getFC(int f, int c) {
		if( f == c )
			return null;
		
		if( f > c )
			return getFC(c, f);
		
		int i = f*this.n + c - (f*f+3*f+2)/2;
		return this.vector[i];
	}
	
	public void verMatrizSimetrica() {
		System.out.println("Matriz simetrica");
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++)
				System.out.print(String.format("%-3s ", getFC(i, j) == null ? "-" : String.valueOf(getFC(i, j))));
			System.out.println();
		}
	}

	@Override
	public String toString() {
		return "MatrizSimetrica " + Arrays.toString(vector);
	}
}
