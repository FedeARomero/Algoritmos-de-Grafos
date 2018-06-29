package paquete;

import java.util.Comparator;

public class Nodo implements Comparator<Nodo>{

	private int posicion;
	private int valor;
	
	public Nodo() {
		// TODO Auto-generated constructor stub
	}
	
	public Nodo(int posicion, int valor) {
		this.posicion = posicion;
		this.valor = valor;
	}
	
	public Nodo(int valor) {
		this.posicion = 0;
		this.valor = valor;
	}

	public int posicion() {
		return this.posicion;
	}

	public int valor() {
		return this.valor;
	}

	@Override
	public String toString() {
		return "Nodo [" + valor + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (posicion != other.posicion)
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}

	@Override
	public int compare(Nodo uno, Nodo dos) {
		// TODO Auto-generated method stub
		return uno.valor - dos.valor;
	}
	
	
}
