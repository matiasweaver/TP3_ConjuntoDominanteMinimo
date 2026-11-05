package presenter;

import java.util.HashSet;

import java.util.List;

import java.util.Set;

import javax.swing.SwingWorker;

import model.Backtracking;

import model.Goloso;

import model.Grafo;

import model.Model;

import teststress.MetodosAuxiliares;

import teststress.TestStressGUI;

import view.ConjuntoGeneradorMinimoGUI;

import interfaces.Solver;

public class Presenter {
	private Model model;
	private ConjuntoGeneradorMinimoGUI view;
	
	@SuppressWarnings("unused")
	private String rutaArchivo;
	private SwingWorker<Void, String> workerStress;

	public Presenter(Model model, ConjuntoGeneradorMinimoGUI view) {
		this.model = model;
		this.view = view;
	}

	public void archivoSeleccionado(String ruta) {
		this.rutaArchivo = ruta;

		try {
			this.model.cargarArchivoDesdeJSON(ruta);
			this.view.mostrarMensajeExito("Grafo cargado exitosamente. Nodos: " + this.model.getGrafo().tamano() + "\nPresione 'Ejecutar' para visualizar los resultados.");

		}catch(Exception ex){
			view.mostrarError("No se pudo cargar el archivo. Tiene que ser de tipo .json");
			this.rutaArchivo = null;
		}
	}

	public void ejecutarAlgoritmo() {
		if (model.getGrafo() == null) {
			view.mostrarError("Primero selecciona un archivo JSON.");
			return;
		}

		Set<Integer> solucionGoloso = ejecutarEstrategia(new Goloso());

		Set<Integer> solucionBacktracking = ejecutarEstrategia(new Backtracking()); 

		view.mostrarGrafos(model.getGrafo(), solucionGoloso, solucionBacktracking);
	}

	private Set<Integer> ejecutarEstrategia(Solver estrategia) {
		model.setSolver(estrategia);
		model.ejecutarAlgoritmo();

		return new HashSet<>(model.getResultado()); 
	}

	public void limpiar() {
		this.rutaArchivo = null;
		this.model.setGrafo(null); 
		this.view.limpiarPantalla();
	}

	public void ejecutarTestStress() {
		TestStressGUI testStressGUI = new TestStressGUI(this);
		testStressGUI.mostrarVentana();

		workerStress = new SwingWorker<Void, String>() {
			@Override
			protected Void doInBackground() throws Exception {
				int cantidadVertices = 2;

				while (!isCancelled()) {
					Grafo g = MetodosAuxiliares.generarGrafoAleatorio(cantidadVertices, 0.5);

					Backtracking bt = new Backtracking();
					bt.resolver(g);
					long tiempoBT = bt.getTiempoEjecucion();

					publish("Grafo tamaño: " + cantidadVertices + " | Tiempo Backtracking: " + tiempoBT + " ms");

					cantidadVertices++;

					Thread.sleep(50); 
				}
				return null;
			}

			@Override
			protected void process(List<String> chunks) {

				for (String mensaje : chunks) {
					testStressGUI.agregarMensaje(mensaje);
				}
			}
		};

		workerStress.execute();
	}

	public void detenerTestStress() {
		if (this.workerStress != null && !this.workerStress.isCancelled()) {
			this.workerStress.cancel(true);
		}
	}
}