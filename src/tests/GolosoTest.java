package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Set;

import org.junit.Before;

import model.Goloso;

import model.Grafo;

public class GolosoTest {
	private Grafo grafo;
	private Goloso solverGoloso;

	@Before
	public void setUp() {
		grafo = new Grafo(6);
		solverGoloso = new Goloso();

		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 4);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 4);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(3, 4);
		grafo.agregarArista(3, 5);
	}

	@Test
	public void testGrafoConjuntoMinimo() {
		solverGoloso.resolver(grafo);
		Set<Integer> resultado = solverGoloso.getConjuntoDominante();

		assertEquals(2, resultado.size());

		assertTrue(resultado.contains(1));
		assertTrue(resultado.contains(3));
	}
	
	@Test
    public void testGrafoVacio() {
        Grafo grafoVacio = new Grafo(0);
        solverGoloso.resolver(grafoVacio);
        
        assertEquals(0, solverGoloso.getConjuntoDominante().size());
    }
	
	@Test
	public void testGrafoDesconectado() {
		Grafo grafoDesconectado = new Grafo(4);
		solverGoloso.resolver(grafoDesconectado);
		Set<Integer> resultado = solverGoloso.getConjuntoDominante();

		assertEquals(4, resultado.size());
	}

	@Test
	public void testGrafoCompleto() {
		Grafo grafoCompleto = new Grafo(4);
		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 4; j++) {
				grafoCompleto.agregarArista(i, j);
			}
		}

		solverGoloso.resolver(grafoCompleto);
		Set<Integer> resultado = solverGoloso.getConjuntoDominante();

		assertEquals(1, resultado.size());
	}
}