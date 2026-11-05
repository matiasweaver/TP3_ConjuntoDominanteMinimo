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
/*	
    Original 
	public void resolver(Grafo g) {
		// Obligatorio: Vaciar la colección para no arrastrar basura de ejecuciones
		// previas
		limpiarEjecucionAnterior();

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
*/
	@Override
    public void resolver(Grafo grafo) {
		
        limpiarEjecucionAnterior();
        
        int totalVertices = grafo.tamano();
        
        if (totalVertices == 0) {
            return;
        }

       
        boolean[] cubierto = new boolean[totalVertices];
        int cantidadCubiertos = 0;

        // Bucle principal limpio e intuitivo
        while (cantidadCubiertos < totalVertices) {
            int mejorVertice = seleccionarMejorVertice(grafo, cubierto);
            
            registrarEnSolucion(mejorVertice);
            
            cantidadCubiertos += marcarComoCubierto(grafo, mejorVertice, cubierto);
        }
    }

	private void registrarEnSolucion(int mejorVertice) {
		this.conjuntoDominante.add(mejorVertice);
	}
	
	private int seleccionarMejorVertice(Grafo grafo, boolean[] cubierto) {
        int mejorVertice = -1;
        int maxNuevosCubiertos = -1;

        for (int i = 0; i < grafo.tamano(); i++) {
            int potencialesCubiertos = contarNuevosCubiertos(grafo, i, cubierto);

            if (potencialesCubiertos > maxNuevosCubiertos) {
                maxNuevosCubiertos = potencialesCubiertos;
                mejorVertice = i;
            }
        }

        return mejorVertice;
    }
		
    private int contarNuevosCubiertos(Grafo grafo, int vertice, boolean[] cubierto) {
        int nuevosCubiertos = 0;

        if (!cubierto[vertice]) {
            nuevosCubiertos++;
        }

        for (int vecino : grafo.vecinos(vertice)) {
            if (!cubierto[vecino]) {
                nuevosCubiertos++;
            }
        }

        return nuevosCubiertos;
    }
    
    private int marcarComoCubierto(Grafo grafo, int vertice, boolean[] cubierto) {
        int nuevosCubiertos = 0;

        if (!cubierto[vertice]) {
            cubierto[vertice] = true;
            nuevosCubiertos++;
        }

        for (int vecino : grafo.vecinos(vertice)) {
            if (!cubierto[vecino]) {
                cubierto[vecino] = true;
                nuevosCubiertos++;
            }
        }

        return nuevosCubiertos;
    }

    
		
	private void limpiarEjecucionAnterior() {
		this.conjuntoDominante.clear();
	}
}
