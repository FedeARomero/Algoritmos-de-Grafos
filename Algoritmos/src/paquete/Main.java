package paquete;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		Grafo grafo = new Grafo(8, 14);
		grafo.agregarArista(1, 2, 8);
		grafo.agregarArista(1, 4, 1);
		grafo.agregarArista(1, 8, 2);
		grafo.agregarArista(2, 3, 2);
		grafo.agregarArista(2, 7, 8);
		grafo.agregarArista(3, 7, 5);
		grafo.agregarArista(3, 8, 3);
		grafo.agregarArista(4, 6, 2);
		grafo.agregarArista(4, 8, 5);
		grafo.agregarArista(5, 6, 3);
		grafo.agregarArista(5, 8, 2);
		grafo.agregarArista(6, 7, 5);
		grafo.agregarArista(6, 8, 9);
		grafo.agregarArista(7, 8, 4);
		
		grafo.verMatriz();
		System.out.println(Arrays.toString(grafo.recorridoDFS(1)));
		System.out.println(Arrays.toString(grafo.recorridoBFS(1)));
		Arista[] kruskal = grafo.kruskal();
		System.out.println("Arbol");
		for (int i = 0; i < kruskal.length; i++) {
			System.out.println(kruskal[i]);
		}
		
		Arista[] prim = grafo.kruskal();
		System.out.println("Arbol");
		for (int i = 0; i < prim.length; i++) {
			System.out.println(prim[i]);
		}
		
		GrafoDirigido dirigido = new GrafoDirigido(3, 4);
		dirigido.agregarArista(1, 2, 8);
		dirigido.agregarArista(2, 1, 3);
		dirigido.agregarArista(3, 2, 2);
		dirigido.agregarArista(1, 3, 5);
				
		Integer[][] floyd = dirigido.floyd();
		
		for (int i = 0; i < floyd.length; i++) {
			for (int j = 0; j < floyd.length; j++)
				System.out.print(floyd[i][j] + " ");
			System.out.println();
		}
		
		boolean[][] warshall = dirigido.warshall();
		
		for (int i = 0; i < warshall.length; i++) {
			for (int j = 0; j < warshall.length; j++)
				System.out.print(warshall[i][j] + " ");
			System.out.println();
		}
	}

}
