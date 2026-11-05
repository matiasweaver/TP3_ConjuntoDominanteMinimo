package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.Set;

import org.junit.Before;

import model.Goloso;
import model.Grafo;
import model.Model;

public class GolosoTest {
	private Model modelo;
	private Grafo grafo;

	@Before
	public void setUp() {
		// Se inicializa el modelo y el grafo de 6 vértices antes de cada test
		modelo = new Model();
		grafo = new Grafo(6);

		// Traducción de la Figura 1 a índices base cero (Nodo 1 = 0, Nodo 6 = 5)
		grafo.agregarArista(0, 1); // Arista 1-2
		grafo.agregarArista(0, 4); // Arista 1-5

		grafo.agregarArista(1, 2); // Arista 2-3
		grafo.agregarArista(1, 4); // Arista 2-5

		grafo.agregarArista(2, 3); // Arista 3-4

		grafo.agregarArista(3, 4); // Arista 4-5
		grafo.agregarArista(3, 5); // Arista 4-6

		// Inyectamos las dependencias
		modelo.setGrafo(grafo);
		modelo.setSolver(new Goloso());
	}

	@Test
	public void testGrafoFigura1() {
		// Ejecución
		modelo.ejecutarAlgoritmo();
		Set<Integer> resultado = modelo.getResultado();

		// Verificación
		// 1. El tamaño del conjunto debe ser exactamente 2
		assertEquals(2, resultado.size());

		// 2. El conjunto debe contener a los nodos 2 y 4 (índices 1 y 3)
		assertTrue(resultado.contains(1));
		assertTrue(resultado.contains(3));
	}
}
