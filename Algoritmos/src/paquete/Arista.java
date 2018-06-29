package paquete;

import java.util.Comparator;

public class Arista implements Comparator<Arista>{
	
	private Nodo origen;
	private Nodo destino;
	private int peso;
	
	public Arista() {
		// TODO Auto-generated constructor stub
	}
	
	public Arista(Nodo origen, Nodo destino) {
		this.origen = origen;
		this.destino = destino;
		this.peso = 0;
	}

	public Arista(Nodo origen, Nodo destino, int peso) {
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
	}
	
	public Nodo origen() {
		return this.origen;
	}
	
	public Nodo destino() {
		return this.destino;
	}
	
	public int peso() {
		return this.peso;
	}

	@Override
	public String toString() {
		return "Arista [" + this.origen + " <-- " + this.peso + " -->" + this.destino + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arista other = (Arista) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino) && !destino.equals(other.origen))
			return false;
		if (origen == null) {
			if (other.origen != null)
				return false;
		} else if (!origen.equals(other.origen) && !origen.equals(other.destino))
			return false;
		if (peso != other.peso)
			return false;
		return true;
	}

	@Override
	public int compare(Arista uno, Arista dos) {
		return uno.peso - dos.peso;
	}
}
