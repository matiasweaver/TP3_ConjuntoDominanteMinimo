package view;

import model.Grafo;

import presenter.Presenter;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.awt.BorderLayout;

import java.awt.Color;

import com.mxgraph.layout.mxCircleLayout;

import com.mxgraph.swing.mxGraphComponent;

import com.mxgraph.view.mxGraph;

import javax.swing.BorderFactory;

import javax.swing.ImageIcon;

import javax.swing.JButton;

import javax.swing.JFileChooser;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JSeparator;

import javax.swing.JTextArea;

import javax.swing.JTextField;

import javax.swing.SwingConstants;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.Set;

public class ConjuntoGeneradorMinimoGUI {

	private Presenter presenter;
	private JFrame GUIPrincipal;
	private JLabel txtAlgoritmoGoloso, txtBacktracking;
	private JTextField textoCargarArchivo;

	@SuppressWarnings("unused")
	private JButton btnExaminarArchivo, btnEjecutar, btnLimpiar, btnTestStress;
	private JTextArea txtConjuntoMinimo1, txtConjuntoMinimo2;
	private JScrollPane scrollConjunto1, scrollConjunto2;
	private JPanel panelGrafoGoloso, panelGrafoBacktracking;

	public ConjuntoGeneradorMinimoGUI() {
		inicializarVentanaPrincipal();

		inicializarBotonCargarArchivo();

		inicializarBotonesAccion(); 

		inicializarBotonesAlgoritmos();

		iniciaizarTextosConjuntoMinimo();

		inicializarPanelesGrafos();
	}

	public void mostrarVentana() {
		this.GUIPrincipal.setVisible(true);
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	private void inicializarVentanaPrincipal() {
		GUIPrincipal = new JFrame();
		GUIPrincipal.setBounds(100, 100, 1000, 700);
		GUIPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUIPrincipal.setResizable(false);
		GUIPrincipal.getContentPane().setLayout(null);
	}

	private void inicializarBotonCargarArchivo() {
		textoCargarArchivo = new JTextField();
		textoCargarArchivo.setText("Cargar archivo JSON");
		textoCargarArchivo.setEditable(false);
		textoCargarArchivo.setColumns(10);
		textoCargarArchivo.setBounds(20, 24, 308, 29);
		GUIPrincipal.getContentPane().add(textoCargarArchivo);

		btnExaminarArchivo = new JButton();
		btnExaminarArchivo.setText("");
		btnExaminarArchivo.setIcon(new ImageIcon(ConjuntoGeneradorMinimoGUI.class.getResource("/recursos/19460311.png")));
		btnExaminarArchivo.setBounds(338, 24, 35, 35);
		GUIPrincipal.getContentPane().add(btnExaminarArchivo);

		btnExaminarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();

				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos JSON (*.json)", "json");
				fileChooser.setFileFilter(filtro);

				int seleccion = fileChooser.showOpenDialog(GUIPrincipal);

				if (seleccion == JFileChooser.APPROVE_OPTION) {
					String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
					textoCargarArchivo.setText(rutaArchivo); 

					if (presenter != null) {
						presenter.archivoSeleccionado(rutaArchivo); 
					}
				}
			}
		});
	}

	private void inicializarBotonesAccion() {

		btnEjecutar = crearBotonEstilizado("Ejecutar", new Color(92, 200, 92), 383, 27, 89, 23, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(presenter != null) {
					presenter.ejecutarAlgoritmo();
				}
			}
		});

		btnTestStress = crearBotonEstilizado("Prueba de stress", new Color(255, 102, 102), 645, 27, 155, 23, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(presenter != null) {
					presenter.ejecutarTestStress(); 
				}
			}
		});

		btnLimpiar = crearBotonEstilizado("Limpiar", new Color(255, 102, 102), 840, 27, 89, 23, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(presenter != null) {
					presenter.limpiar();
				}
			}
		});
	}

	private JButton crearBotonEstilizado(String texto, Color colorFondo, int x, int y, int ancho, int alto, ActionListener accion) {
		JButton boton = new JButton(texto);
		boton.setBackground(colorFondo);
		boton.setForeground(Color.BLACK);
		boton.setFocusPainted(false);
		boton.setContentAreaFilled(false);
		boton.setOpaque(true);
		boton.setBounds(x, y, ancho, alto);
		boton.addActionListener(accion);

		GUIPrincipal.getContentPane().add(boton);
		return boton;
	}

	private void inicializarBotonesAlgoritmos() {
		txtAlgoritmoGoloso = new JLabel();
		txtAlgoritmoGoloso.setText("ALGORITMO GOLOSO");
		txtAlgoritmoGoloso.setBounds(153, 117, 155, 14);
		GUIPrincipal.getContentPane().add(txtAlgoritmoGoloso);

		txtBacktracking = new JLabel();
		txtBacktracking.setText("BACKTRACKING");
		txtBacktracking.setBounds(682, 117, 155, 14);
		GUIPrincipal.getContentPane().add(txtBacktracking);	
	}

	private void iniciaizarTextosConjuntoMinimo() {
		txtConjuntoMinimo1 = crearPanelResultado(20, scrollConjunto1);
		txtConjuntoMinimo2 = crearPanelResultado(510, scrollConjunto2);
	}

	private JTextArea crearPanelResultado(int x, JScrollPane scrollAsignado) {
		JLabel lblTitulo = new JLabel("CONJUNTO MÍNIMO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER); 
		lblTitulo.setBounds(x, 490, 419, 20);
		GUIPrincipal.getContentPane().add(lblTitulo);

		JSeparator separador = new JSeparator();
		separador.setBounds(x, 515, 419, 2);
		GUIPrincipal.getContentPane().add(separador);

		JTextArea txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);

		scrollAsignado = new JScrollPane(txtArea);
		scrollAsignado.setBounds(x, 525, 419, 130); 
		GUIPrincipal.getContentPane().add(scrollAsignado);

		return txtArea;
	}

	private void inicializarPanelesGrafos() {
		panelGrafoGoloso = new JPanel();
		panelGrafoGoloso.setBounds(20, 140, 419, 340);
		panelGrafoGoloso.setLayout(new BorderLayout());
		panelGrafoGoloso.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		GUIPrincipal.getContentPane().add(panelGrafoGoloso);

		panelGrafoBacktracking = new JPanel();
		panelGrafoBacktracking.setBounds(510, 140, 419, 340);
		panelGrafoBacktracking.setLayout(new BorderLayout());
		panelGrafoBacktracking.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		GUIPrincipal.getContentPane().add(panelGrafoBacktracking);
	}

	public void mostrarGrafos(Grafo grafoLogico, Set<Integer> solucionGoloso, Set<Integer> solucionBacktracking) {
		panelGrafoGoloso.removeAll();
		panelGrafoBacktracking.removeAll();

		mxGraphComponent componenteGoloso = crearComponenteGrafo(grafoLogico, solucionGoloso);
		mxGraphComponent componenteBacktracking = crearComponenteGrafo(grafoLogico, solucionBacktracking);

		panelGrafoGoloso.add(componenteGoloso, BorderLayout.CENTER);
		panelGrafoBacktracking.add(componenteBacktracking, BorderLayout.CENTER);

		txtConjuntoMinimo1.setText("Tamaño: " + solucionGoloso.size() + " - Nodos: " + solucionGoloso.toString());
		txtConjuntoMinimo2.setText("Tamaño: " + solucionBacktracking.size() + " - Nodos: " + solucionBacktracking.toString());

		panelGrafoGoloso.revalidate();
		panelGrafoGoloso.repaint();
		panelGrafoBacktracking.revalidate();
		panelGrafoBacktracking.repaint();
	}

	private mxGraphComponent crearComponenteGrafo(Grafo grafoLogico, Set<Integer> conjuntoDominante) {

		mxGraph grafoVisual = new mxGraph() {
			@Override
			public boolean isCellMovable(Object cell) {
				return !getModel().isEdge(cell);
			}
			
			@Override
			public boolean isCellDisconnectable(Object cell, Object terminal, boolean source) {
				return false;
			}
		};

		grafoVisual.setCellsBendable(false);

		Object capaBase = grafoVisual.getDefaultParent();

		int cantidadNodos = grafoLogico.tamano();
		Object[] nodosVisuales = new Object[cantidadNodos];

		int anchoNodo = 30;
		int altoNodo = 30;

		grafoVisual.getModel().beginUpdate();

		try {
			for (int i = 0; i < cantidadNodos; i++) {
				String colorNodo;

				if (conjuntoDominante != null && conjuntoDominante.contains(i)) {
					colorNodo = "shape=ellipse;fillColor=#ff6666"; 
				} else {
					colorNodo = "shape=ellipse;fillColor=lightblue";
				}

				nodosVisuales[i] = grafoVisual.insertVertex(capaBase, null, String.valueOf(i), 0, 0, anchoNodo, altoNodo, colorNodo);
			}

			for (int i = 0; i < cantidadNodos; i++) {
				for (int j = i + 1; j < cantidadNodos; j++) {
					if (grafoLogico.existeArista(i, j)) {
						grafoVisual.insertEdge(capaBase, null, "", nodosVisuales[i], nodosVisuales[j], "endArrow=none");
					}
				}
			}
		} finally {
			grafoVisual.getModel().endUpdate();
		}

		mxCircleLayout layoutCircular = new mxCircleLayout(grafoVisual);
		layoutCircular.execute(capaBase);

		mxGraphComponent componenteFinal = new mxGraphComponent(grafoVisual);
		componenteFinal.setConnectable(false); 

		return componenteFinal;
	}

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(GUIPrincipal, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMensajeExito(String mensaje) {
		JOptionPane.showMessageDialog(GUIPrincipal, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}

	public void limpiarPantalla() {
		textoCargarArchivo.setText("Cargar archivo JSON");

		panelGrafoGoloso.removeAll();
		panelGrafoBacktracking.removeAll();

		txtConjuntoMinimo1.setText("");
		txtConjuntoMinimo2.setText("");

		panelGrafoGoloso.revalidate();
		panelGrafoGoloso.repaint();
		panelGrafoBacktracking.revalidate();
		panelGrafoBacktracking.repaint();
	}
}