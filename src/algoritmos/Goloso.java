package algoritmos;

import java.util.HashSet;
import java.util.Set;
import grafo.Grafo;
import interfaces.Solver;

public class Goloso implements Solver {
	private Set<Integer> conjuntoDominante;

	public Set<Integer> getConjuntoDominante() {
		return this.conjuntoDominante;
	}

	public Goloso() {
		this.conjuntoDominante = new HashSet<Integer>();
	}

	public void resolver(Grafo g) {
		// Obligatorio: Vaciar la colección para no arrastrar basura de ejecuciones
		// previas
		this.conjuntoDominante.clear();

		int totalVertices = g.tamano();
		if (totalVertices == 0)
			return;

		boolean[] cubierto = new boolean[totalVertices];
		int cantidadCubiertos = 0;

		// 2. Bucle principal: iterar hasta que todos los nodos estén cubiertos
		while (cantidadCubiertos < totalVertices) {
			int mejorVertice = -1;
			int maxNuevosCubiertos = -1;

			// 3. La Heurística: Evaluar todos los vértices para encontrar el óptimo local
			for (int i = 0; i < totalVertices; i++) {
				int potencialesCubiertos = 0;

				// Contar si el vértice 'i' se cubriría a sí mismo
				if (!cubierto[i]) {
					potencialesCubiertos++;
				}

				// Contar cuántos de sus vecinos están sin cubrir
				for (int vecino : g.vecinos(i)) {
					if (!cubierto[vecino]) {
						potencialesCubiertos++;
					}
				}

				// Seleccionar el vértice que cubra la mayor cantidad de nodos nuevos
				if (potencialesCubiertos > maxNuevosCubiertos) {
					maxNuevosCubiertos = potencialesCubiertos;
					mejorVertice = i;
				}
			}

			// 4. Actualización de estado
			// Agregar el vértice ganador al conjunto solución
			this.conjuntoDominante.add(mejorVertice);

			// Marcar el vértice ganador como cubierto
			if (!cubierto[mejorVertice]) {
				cubierto[mejorVertice] = true;
				cantidadCubiertos++;
			}

			// Marcar a todos los vecinos del vértice ganador como cubiertos
			for (int vecino : g.vecinos(mejorVertice)) {
				if (!cubierto[vecino]) {
					cubierto[vecino] = true;
					cantidadCubiertos++;
				}
			}
		}
	}
}
