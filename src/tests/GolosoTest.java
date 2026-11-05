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
		modelo = new Model();
		grafo = new Grafo(6);

		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 4);

		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 4);

		grafo.agregarArista(2, 3);

		grafo.agregarArista(3, 4);
		grafo.agregarArista(3, 5);

		modelo.setGrafo(grafo);
		modelo.setSolver(new Goloso());
	}

	@Test
	public void testGrafoFigura1() {
		modelo.ejecutarAlgoritmo();
		Set<Integer> resultado = modelo.getResultado();

		assertEquals(2, resultado.size());

		assertTrue(resultado.contains(1));
		assertTrue(resultado.contains(3));
	}
}