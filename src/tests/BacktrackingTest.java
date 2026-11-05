package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.Before;

import org.junit.jupiter.api.Test;

import model.Backtracking;

import model.Goloso;

import model.Grafo;

class BacktrackingTest {
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
		
		assertEquals("El conjunto mínimo de este grafo debe tener 2 nodos", 2, resultado.size());
	}
	
	@Test
	public void testGrafoVacio_RetornaConjuntoVacio() {
		Grafo grafoVacio = new Grafo(0);
		solverBacktracking.resolver(grafoVacio);
		
		assertEquals("Un grafo sin nodos debe devolver un conjunto de tamaño 0", 0, solverBacktracking.getConjuntoDominante().size());
	}

	@Test
	public void testGrafoDesconectado_RetornaTodosLosNodos() {
		// Un grafo donde ningún nodo está conectado con otro
		Grafo grafoDesconectado = new Grafo(3); 
		
		solverBacktracking.resolver(grafoDesconectado);
		Set<Integer> resultado = solverBacktracking.getConjuntoDominante();

		assertEquals("Al no haber aristas, el conjunto dominante debe incluir todos los nodos", 3, resultado.size());
		assertTrue(resultado.contains(0));
		assertTrue(resultado.contains(1));
		assertTrue(resultado.contains(2));
	}

	@Test
	public void testGrafoEstrella_RetornaElCentro() {
		Grafo grafoEstrella = new Grafo(4);
		// El nodo 0 es el centro, conectado a todos los demás
		grafoEstrella.agregarArista(0, 1);
		grafoEstrella.agregarArista(0, 2);
		grafoEstrella.agregarArista(0, 3);

		solverBacktracking.resolver(grafoEstrella);
		Set<Integer> resultado = solverBacktracking.getConjuntoDominante();

		assertEquals("El centro de la estrella cubre todo el grafo por sí solo", 1, resultado.size());
		assertTrue("El nodo elegido debe ser obligatoriamente el central (0)", resultado.contains(0));
	}
}