package teststress;

import java.util.Random;

import model.Grafo;

public class GeneradorGrafo {
	public static Grafo generarGrafoAleatorio(int numVertices, double probabilidad) {
		Grafo g = new Grafo(numVertices);
		Random rand = new Random();
		
		for (int i = 0; i < numVertices; i++) {
			for (int j = i + 1; j < numVertices; j++) {
				// Genera un número entre 0.0 y 1.0. Si es menor a 0.5 (50%), agrega la arista
				if (rand.nextDouble() < probabilidad) {
					g.agregarArista(i, j);
				}
			}
		}
		return g;
	}
}
