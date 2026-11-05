package presenter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingWorker;

import algoritmos.Backtracking;
import algoritmos.Goloso;
import grafo.Grafo;
import model.Model;
import teststress.GeneradorGrafo;
import view.ConjuntoGeneradorMinimoGUI;
import view.TestStressGUI;

public class Presenter {
	private Model model;
	private ConjuntoGeneradorMinimoGUI view;
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
			this.view.mostrarMensajeExito("Grafo cargado exitosamente. Nodos: " + this.model.getGrafo().tamano());

		}catch(Exception ex){
			view.mostrarError("No se pudo cargar el archivo: " + ex.getMessage());
			this.rutaArchivo = null;
		}
	}

	private Set<Integer> instanciarYEjecutarGoloso() {
		Goloso goloso = new Goloso();
		model.setSolver(goloso);
		model.ejecutarAlgoritmo();
		return model.getResultado();
	}

	private Set<Integer> instanciarYEjecutarBacktracking() {
		Backtracking backtracking = new Backtracking();
		model.setSolver(backtracking);
		model.ejecutarAlgoritmo();
		return model.getResultado();
	}

	public void ejecutarAlgoritmo() {
		if(model.getGrafo() == null) {
			view.mostrarError("Primero selecciona un archivo JSON.");
			return;
		}

		//guardamos el resultado para mostrarlo
		Set<Integer> solucionGoloso = instanciarYEjecutarGoloso();
		//Set<Integer> solucionBacktracking = instanciarYEjecutarBacktracking();

		Set<Integer> solucionBacktracking = instanciarYEjecutarBacktracking(); // Mock temporal hasta implementar backtracking

		view.mostrarGrafos(model.getGrafo(), solucionGoloso, solucionBacktracking);
	}

	public void limpiar() {
		this.rutaArchivo = null;
		this.model.setGrafo(null); // Vaciamos el grafo de la memoria

		// Tendrás que agregar este método en ConjuntoGeneradorMinimoGUI
		// para que vacíe los paneles y limpie el recuadro de texto de la ruta.
		this.view.limpiarPantalla();
	}

	public void ejecutarTestStress() {
		// 1. Inicializamos la ventana y le pasamos ESTE presenter (this)
		TestStressGUI testStressGUI = new TestStressGUI(this);
		testStressGUI.mostrarVentana();

		// 2. Inicializamos el hilo en segundo plano
		workerStress = new SwingWorker<Void, String>() {
			@Override
			protected Void doInBackground() throws Exception {
				int cantidadVertices = 2;

				// El bucle corre mientras el hilo no haya sido cancelado
				while (!isCancelled()) {
					// Genera un grafo aleatorio con 50% de probabilidad (0.5)
					Grafo g = GeneradorGrafo.generarGrafoAleatorio(cantidadVertices, 0.5);

					// Ejecuta el algoritmo
					Backtracking bt = new Backtracking();
					bt.resolver(g);
					long tiempoBT = bt.getTiempoEjecucion();

					// Envía el texto a la interfaz (esto invoca automáticamente a process)
					publish("Grafo tamaño: " + cantidadVertices + " | Tiempo Backtracking: " + tiempoBT + " ms");

					cantidadVertices++;

					// Pequeña pausa para no saturar la CPU de golpe y ver el progreso
					Thread.sleep(50); 
				}
				return null;
			}

			@Override
			protected void process(List<String> chunks) {
				// Este método se ejecuta en el hilo de la UI, es seguro actualizar la vista
				for (String mensaje : chunks) {
					testStressGUI.agregarMensaje(mensaje);
				}
			}
		};

		// 3. Iniciamos el hilo
		workerStress.execute();
	}

	public void detenerTestStress() {
		// Si el hilo existe y todavía está corriendo, lo cancelamos
		if (this.workerStress != null && !this.workerStress.isCancelled()) {
			this.workerStress.cancel(true);
		}
	}
}
