package paquete;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Grafo {

	private Nodo[] nodos;
	private Arista[] aristas;
	private int cantidadAristas;
	private MatrizSimetrica adyacencia;
	
	public Grafo() {
		
	}
	
	public Grafo(int n, int a) {
		this.nodos = new Nodo[n];
		this.aristas = new Arista[a];
		for (int i = 0; i < nodos.length; i++)
			this.nodos[i] = new Nodo(i, i+1);
		this.cantidadAristas = 0;
		this.adyacencia = new MatrizSimetrica(n);
	}
	
	public void agregarArista(int origen, int destino, int peso) {
		this.adyacencia.setFC(origen-1, destino-1, peso);
		this.aristas[this.cantidadAristas++] = new Arista(this.nodos[origen-1], this.nodos[destino-1], peso);
	}
	
	public void verMatriz() {
		this.adyacencia.verMatrizSimetrica();
	}
	
	public Arista[] prim() {
		boolean[] visitados = new boolean[this.nodos.length];
		int cantidadAtistas = 0;
		Arista[] aristasPrim = new Arista[this.nodos.length - 1];
		PriorityQueue<Arista> monticulo = new PriorityQueue<>(new Arista());
		Arista[] vecinos = vecinos(0);
		
		visitados[0] = true;
		for (int i = 0; i < vecinos.length; i++)
			monticulo.add(vecinos[i]);
		
		while( cantidadAtistas != (this.nodos.length - 1) ) {
			Arista actual = monticulo.poll();
			int pos = actual.destino().posicion();
			
			if( visitados[pos] == false ) {
				visitados[pos] = true;
				aristasPrim[cantidadAtistas++] = actual;
				
				vecinos = vecinos(pos);
				for (int i = 0; i < vecinos.length; i++)
				if ( visitados[vecinos[i].destino().posicion()] == false )
					monticulo.add(vecinos[i]);
			}
		}
		monticulo.clear();
		return aristasPrim;
	}
	
	private Arista[] vecinos(int nodo) {
		int cantidadVecinos = 0;
		
		for (int i = 0; i < this.nodos.length; i++)
		if( this.adyacencia.getFC(nodo, i) != null )
			cantidadVecinos++;
		
		Arista[] vecinos = new Arista[cantidadVecinos];
		cantidadVecinos = 0;
		
		for (int i = 0; i < this.nodos.length; i++)
		if( this.adyacencia.getFC(nodo, i) != null )
			vecinos[cantidadVecinos++] = new Arista(this.nodos[nodo], this.nodos[i], this.adyacencia.getFC(nodo, i).intValue());
		
		return vecinos;
	}
	
	public Arista[] kruskal() {
		int cantidaAristas = 0;
		Arista[] aristasKruskal = new Arista[this.nodos.length - 1];
		int[] padre = new int[this.nodos.length];
		
		for (int i = 0; i < padre.length; i++)
			padre[i] = i;
		
		Arrays.sort(this.aristas, new Arista());
		
		int i = 0;
		while( cantidaAristas != (this.nodos.length - 1) ) {
			int origen = this.aristas[i].origen().posicion();
			int destino = this.aristas[i].destino().posicion();
			if( !sameRoot(padre, origen, destino) ) {
				aristasKruskal[cantidaAristas++] = this.aristas[i];
				union(padre, origen, destino);
			}
			i++;
		}
		
		return aristasKruskal;
	}
	
	private int find(int[] root, int i) {
		return i == root[i] ? i : ( root[i] = find(root, root[i]) );
	}
	
	private void union(int[] root, int i, int j) {
		root[ find(root, i) ] = find(root, j);
	}
	
	private boolean sameRoot(int[]root, int i, int j) {
		return find(root, i) == find(root, j);
	}
	
	
}
