package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class TestStressGUI {

	private JFrame frame;
	private JButton btnDetener;

	public TestStressGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(350, 150, 600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnDetener = new JButton();
		btnDetener.setText("Detener test");
		btnDetener.setBackground(new Color(92, 200, 92));
		btnDetener.setForeground(Color.BLACK);
		btnDetener.setFocusPainted(false); 
		btnDetener.setContentAreaFilled(false); 
		btnDetener.setOpaque(true);
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Faltaría el método de detener test
			}
		});
		btnDetener.setBounds(28, 511, 113, 23);
		frame.getContentPane().add(btnDetener);
	}

	public void mostrarVentana() {
		frame.setVisible(true);
	}
}