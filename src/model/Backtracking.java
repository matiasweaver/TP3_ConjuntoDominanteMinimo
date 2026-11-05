package model;

import java.util.HashSet;

import java.util.Set;

import interfaces.Solver;

public class Backtracking implements Solver {
	private Set<Integer> conjuntoDominante;

	private long tiempoEjecucion;

	public Set<Integer> getConjuntoDominante(){
		return this.conjuntoDominante;
	}

	public long getTiempoEjecucion() {
		return this.tiempoEjecucion;
	}

	@Override
	public void resolver(Grafo g) {
		long inicio = System.currentTimeMillis();

		if (g.tamano() == 0) {
			this.conjuntoDominante = new HashSet<Integer>();
			this.tiempoEjecucion = System.currentTimeMillis() - inicio;
			return;
		}

		// Inicializamos la peor solución posible: todos los vértices del grafo
		this.conjuntoDominante = new HashSet<Integer>();
		for (int i = 0; i < g.tamano(); i++) {
			this.conjuntoDominante.add(i);
		}

		// Comenzamos la exploración recursiva desde el nodo 0 con un conjunto vacío
		Set<Integer> conjuntoParcial = new HashSet<Integer>();
		explorar(g, 0, conjuntoParcial);

		this.tiempoEjecucion = System.currentTimeMillis() - inicio;
	}

	// Algoritmo recursivo para obtener todas las posibles soluciones
	private void explorar(Grafo g, int nodoActual, Set<Integer> conjuntoParcial) {
		if (conjuntoParcial.size() >= this.conjuntoDominante.size()) {
			return;
		}

		if (nodoActual == g.tamano()) {
			if (esConjuntoDominante(g, conjuntoParcial)) {
				this.conjuntoDominante = new HashSet<Integer>(conjuntoParcial);
			}
			return;
		}
		conjuntoParcial.add(nodoActual);
		explorar(g, nodoActual + 1, conjuntoParcial);

		conjuntoParcial.remove(nodoActual);
		explorar(g, nodoActual + 1, conjuntoParcial);
	}

	private boolean esConjuntoDominante(Grafo g, Set<Integer> conjunto) {
		boolean[] cubierto = new boolean[g.tamano()];
		int cantidadCubiertos = 0;

		for (Integer nodo : conjunto) {
			if (!cubierto[nodo]) {
				cubierto[nodo] = true;
				cantidadCubiertos++;
			}
			for (Integer vecino : g.vecinos(nodo)) {
				if (!cubierto[vecino]) {
					cubierto[vecino] = true;
					cantidadCubiertos++;
				}
			}
		}
		return cantidadCubiertos == g.tamano();
	}
}