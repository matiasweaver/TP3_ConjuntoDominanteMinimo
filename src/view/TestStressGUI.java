package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import presenter.Presenter;

public class TestStressGUI {

	private JFrame frame;
	private JButton btnDetener;
	private JTextArea txtResultados;
	private Presenter presenter;

	// Ahora recibe el presenter por parámetro
	public TestStressGUI(Presenter presenter) {
		this.presenter = presenter;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(350, 150, 400, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Test de Stress - Backtracking");

		btnDetener = new JButton();
		btnDetener.setText("Detener test");
		btnDetener.setBackground(new Color(255, 102, 102));
		btnDetener.setForeground(Color.BLACK);
		btnDetener.setFocusPainted(false);
		btnDetener.setContentAreaFilled(false);
		btnDetener.setOpaque(true);
		
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamamos al método del presenter para detener el hilo
				if (presenter != null) {
					presenter.detenerTestStress();
					btnDetener.setEnabled(false); // Deshabilitamos el botón
					agregarMensaje("--- Test detenido por el usuario ---");
				}
			}
		});
		btnDetener.setBounds(130, 410, 130, 30);
		frame.getContentPane().add(btnDetener);

		// Área de texto con Scroll para mostrar los resultados en vivo
		txtResultados = new JTextArea();
		txtResultados.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtResultados);
		scrollPane.setBounds(20, 20, 340, 370);
		frame.getContentPane().add(scrollPane);
		
		// Si el usuario cierra la ventana con la 'X', también detenemos el proceso
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (presenter != null) {
		        	presenter.detenerTestStress();
		        }
		    }
		});
	}

	public void mostrarVentana() {
		frame.setVisible(true);
	}

	// Método para que el presenter inyecte los resultados
	public void agregarMensaje(String mensaje) {
		txtResultados.append(mensaje + "\n");
		// Auto-scroll hacia la última línea
		txtResultados.setCaretPosition(txtResultados.getDocument().getLength());
	}
}