package view;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Set;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import grafo.Grafo;
import presenter.Presenter;


public class ConjuntoGeneradorMinimoGUI {
	
	private TestStressGUI testStressGUI;
	private Presenter presenter;
	private JFrame frameGUIPrincipal;
	private JLabel txtAlgoritmoGoloso, txtBacktracking;
	private JTextField textoCargarArchivo;
	private JButton btnExaminarArchivo, btnEjecutar, btnLimpiar, btnTestStress;
	private JTextArea txtConjuntoMinimo1, txtConjuntoMinimo2;
	private JScrollPane scrollConjunto1, scrollConjunto2;
	private JPanel panelGrafoGoloso, panelGrafoBacktracking;;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConjuntoGeneradorMinimoGUI window = new ConjuntoGeneradorMinimoGUI();
					window.frameGUIPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public ConjuntoGeneradorMinimoGUI() {

		inicializarVentanaPrincipal();

		inicializarBotonCargarArchivo();

		inicializarBotonEjecutar();

		inicializarBotonLimpiar();

		inicializarBotonPruebaStress();

		inicializarBotonesAlgoritmos();

		iniciaizarTextosConjuntoMinimo();

		inicializarPanelesGrafos();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	
	private void inicializarVentanaPrincipal() {
		frameGUIPrincipal = new JFrame();
		frameGUIPrincipal.setBounds(100, 100, 1000, 700);
		frameGUIPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameGUIPrincipal.getContentPane().setLayout(null);
	}

	private void inicializarBotonCargarArchivo() {

		textoCargarArchivo = new JTextField();
		textoCargarArchivo.setText("Cargar archivo JSON");
		textoCargarArchivo.setEditable(false);
		textoCargarArchivo.setColumns(10);
		textoCargarArchivo.setBounds(20, 24, 308, 29);
		frameGUIPrincipal.getContentPane().add(textoCargarArchivo);

		btnExaminarArchivo = new JButton();
		btnExaminarArchivo.setText("");

		btnExaminarArchivo.setIcon(new ImageIcon(ConjuntoGeneradorMinimoGUI.class.getResource("/recursos/19460311.png")));
		btnExaminarArchivo.setBounds(338, 24, 35, 35);
		frameGUIPrincipal.getContentPane().add(btnExaminarArchivo);
	}

	/*	private void inicializarBoton(JButton boton, String texto, int x, int y, int w, int a) {
		boton = new JButton();
		boton.setText(texto);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton.setBounds(805, 27, 155, 23);
		frameGUIPrincipal.getContentPane().add(boton);

	}
	 */

	private void inicializarBotonEjecutar() {
		btnEjecutar = new JButton();
		btnEjecutar.setText("Ejecutar");
		btnEjecutar.setBackground(new Color(92, 200, 92));
		btnEjecutar.setForeground(Color.BLACK);
		btnEjecutar.setFocusPainted(false); 
		btnEjecutar.setContentAreaFilled(false); 
		btnEjecutar.setOpaque(true);
		btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(presenter != null) {
					presenter.ejecutarAlgoritmo();
				}
			}
		});
		btnEjecutar.setBounds(383, 27, 89, 23);
		frameGUIPrincipal.getContentPane().add(btnEjecutar);

	}

	private void inicializarBotonLimpiar() {
		btnLimpiar = new JButton();
		btnLimpiar.setText("Limpiar");
		btnLimpiar.setBackground(new Color(255, 102, 102)); 
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setContentAreaFilled(false);
		btnLimpiar.setOpaque(true);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(presenter != null) {
					presenter.limpiar();
				}
			}
		});
		btnLimpiar.setBounds(840, 27, 89, 23);
		frameGUIPrincipal.getContentPane().add(btnLimpiar);

	}

	private void inicializarBotonPruebaStress() {
		btnTestStress = new JButton();
		btnTestStress.setText("Prueba de stress");
		btnTestStress.setBackground(new Color(255, 102, 102)); 
		btnTestStress.setForeground(Color.BLACK);
		btnTestStress.setFocusPainted(false);
		btnTestStress.setContentAreaFilled(false);
		btnTestStress.setOpaque(true);
		btnTestStress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(presenter != null) {
					presenter.ejecutarTestStress();
					testStressGUI = new TestStressGUI();
					testStressGUI.mostrarVentana();
				}
				testStressGUI = new TestStressGUI();
				testStressGUI.mostrarVentana();
			}
		});
		btnTestStress.setBounds(645, 27, 155, 23);
		frameGUIPrincipal.getContentPane().add(btnTestStress);

	}

	private void inicializarBotonesAlgoritmos() {

		txtAlgoritmoGoloso = new JLabel();
		txtAlgoritmoGoloso.setText("ALGORITMO GOLOSO");
		txtAlgoritmoGoloso.setBounds(153, 117, 155, 14);
		frameGUIPrincipal.getContentPane().add(txtAlgoritmoGoloso);

		txtBacktracking = new JLabel();
		txtBacktracking.setText("BRACKTRACKING");
		txtBacktracking.setBounds(682, 117, 155, 14);
		frameGUIPrincipal.getContentPane().add(txtBacktracking);	

	}

	private void iniciaizarTextosConjuntoMinimo() {
		
		//Algoritmo goloso
	    JLabel lblTituloGoloso = new JLabel("CONJUNTO MÍNIMO");
	    lblTituloGoloso.setHorizontalAlignment(SwingConstants.CENTER); 
	    lblTituloGoloso.setBounds(20, 490, 419, 20);
	    frameGUIPrincipal.getContentPane().add(lblTituloGoloso);

	
	    JSeparator separadorGoloso = new JSeparator();
	    separadorGoloso.setBounds(20, 515, 419, 2);
	    frameGUIPrincipal.getContentPane().add(separadorGoloso);


	    txtConjuntoMinimo1 = new JTextArea();
	    txtConjuntoMinimo1.setEditable(false);
	    txtConjuntoMinimo1.setLineWrap(true);
	    txtConjuntoMinimo1.setWrapStyleWord(true);

	    scrollConjunto1 = new JScrollPane(txtConjuntoMinimo1);
	    scrollConjunto1.setBounds(20, 525, 419, 130); 
	    frameGUIPrincipal.getContentPane().add(scrollConjunto1);


	    //Backtracking
	    JLabel lblTituloBracktracking = new JLabel("CONJUNTO MÍNIMO");
	    lblTituloBracktracking.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTituloBracktracking.setBounds(510, 490, 419, 20);
	    frameGUIPrincipal.getContentPane().add(lblTituloBracktracking);

	    JSeparator separadorBack = new JSeparator();
	    separadorBack.setBounds(510, 515, 419, 2);
	    frameGUIPrincipal.getContentPane().add(separadorBack);


	    txtConjuntoMinimo2 = new JTextArea();
	    txtConjuntoMinimo2.setEditable(false);
	    txtConjuntoMinimo2.setLineWrap(true);
	    txtConjuntoMinimo2.setWrapStyleWord(true);

	    scrollConjunto2 = new JScrollPane(txtConjuntoMinimo2);
	    scrollConjunto2.setBounds(510, 525, 419, 130);
	    frameGUIPrincipal.getContentPane().add(scrollConjunto2);
	}
	

	private void inicializarPanelesGrafos() {

		panelGrafoGoloso = new JPanel();
		panelGrafoGoloso.setBounds(20, 140, 419, 340); 
		panelGrafoGoloso.setLayout(new BorderLayout()); 
		frameGUIPrincipal.getContentPane().add(panelGrafoGoloso);

		panelGrafoBacktracking = new JPanel();
		panelGrafoBacktracking.setBounds(510, 140, 419, 340); 
		panelGrafoBacktracking.setLayout(new BorderLayout()); 
		frameGUIPrincipal.getContentPane().add(panelGrafoBacktracking);
	}

	public void mostrarGrafos(Grafo grafoLogico, Set<Integer> solucionGoloso, Set<Integer> solucionBacktracking) {
		// 1. Limpiar los paneles (por si el usuario presiona "Ejecutar" varias veces)
		panelGrafoGoloso.removeAll();
		panelGrafoBacktracking.removeAll();

		// 2. Crear los componentes de JGraphX (aquí asumo que tienes un método 'crearComponenteGrafo' 
		// similar al que te pasé en respuestas anteriores)
		mxGraphComponent componenteGoloso = crearComponenteGrafo(grafoLogico, solucionGoloso);
		mxGraphComponent componenteBacktracking = crearComponenteGrafo(grafoLogico, solucionBacktracking);

		// 3. Inyectar los componentes en los paneles de tu ventana
		panelGrafoGoloso.add(componenteGoloso, BorderLayout.CENTER);
		panelGrafoBacktracking.add(componenteBacktracking, BorderLayout.CENTER);

		// 4. Actualizar las cajas de texto de abajo
		txtConjuntoMinimo1.setText("Tamaño: " + solucionGoloso.size() + " - Nodos: " + solucionGoloso.toString());
		txtConjuntoMinimo2.setText("Tamaño: " + solucionBacktracking.size() + " - Nodos: " + solucionBacktracking.toString());

		// 5. Refrescar la interfaz gráfica (obligatorio cuando agregas cosas dinámicamente)
		panelGrafoGoloso.revalidate();
		panelGrafoGoloso.repaint();
		panelGrafoBacktracking.revalidate();
		panelGrafoBacktracking.repaint();
	}


	private mxGraphComponent crearComponenteGrafo(Grafo grafoLogico, Set<Integer> conjuntoDominante) {
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		int n = grafoLogico.tamano();
		Object[] vertices = new Object[n];

		graph.getModel().beginUpdate();
		try {
			// Dibujar nodos
			for (int i = 0; i < n; i++) {
				String estilo = (conjuntoDominante != null && conjuntoDominante.contains(i)) 
						? "shape=ellipse;fillColor=#ff6666" 
								: "shape=ellipse;fillColor=lightblue";
				vertices[i] = graph.insertVertex(parent, null, String.valueOf(i), 0, 0, 30, 30, estilo);
			}
			// Dibujar aristas
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (grafoLogico.existeArista(i, j)) {
						graph.insertEdge(parent, null, "", vertices[i], vertices[j], "endArrow=none");
					}
				}
			}
		} finally {
			graph.getModel().endUpdate();
		}

		// Ordenar en círculo
		mxCircleLayout layout = new mxCircleLayout(graph);
		layout.execute(parent);

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setConnectable(false);
		return graphComponent;
	}
	
}
