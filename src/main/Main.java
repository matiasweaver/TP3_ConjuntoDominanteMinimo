package main;

import java.awt.EventQueue;

import model.Model;

import view.ConjuntoGeneradorMinimoGUI;

import presenter.Presenter;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Model modelo = new Model();

					ConjuntoGeneradorMinimoGUI vista = new ConjuntoGeneradorMinimoGUI();

					Presenter presentador = new Presenter(modelo, vista);
		
					vista.setPresenter(presentador);
	
					vista.mostrarVentana();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
