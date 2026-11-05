package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;

import org.junit.Test;

import model.Backtracking;

import model.Grafo;

public class BacktrackingTest {
	private Backtracking solverBacktracking;

	@Before
	public void setUp() {
		solverBacktracking = new Backtracking();
	}
	
	@Test
	public void testGrafoTamanoMinimo() {
		Grafo grafo = new Grafo(6);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 4);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 4);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(3, 4);
		grafo.agregarArista(3, 5);
		
		solverBacktracking.resolver(grafo);
		Set<Integer> resultado = solverBacktracking.getConjuntoDominante();
		
		assertEquals(2, resultado.size());
	}
	
	@Test
	public void testGrafoVacio() {
		Grafo grafoVacio = new Grafo(0);
		solverBacktracking.resolver(grafoVacio);
		
		assertEquals(0, solverBacktracking.getConjuntoDominante().size());
	}

	@Test
	public void testGrafoDesconectado() {
		Grafo grafoDesconectado = new Grafo(3);
		
		solverBacktracking.resolver(grafoDesconectado);
		Set<Integer> resultado = solverBacktracking.getConjuntoDominante();

		assertEquals(3, resultado.size());
		assertTrue(resultado.contains(0));
		assertTrue(resultado.contains(1));
		assertTrue(resultado.contains(2));
	}
}