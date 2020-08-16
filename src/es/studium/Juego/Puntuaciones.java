package es.studium.Juego;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Puntuaciones extends Frame implements WindowListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private BaseDatos basedatos = new BaseDatos();
	Button btnaceptar;
	Button btnimprimir;
	TextArea listado;

	Puntuaciones() {
		setLayout(null);
		setTitle("Puntuaciones");
		setVisible(true);

		addWindowListener(this);

		Label jugador = new Label("Posición");
		jugador.setBounds(30, 40, 80, 30);
		add(jugador);

		Label puntuacion = new Label("Puntuación");
		puntuacion.setBounds(110, 40, 90, 30);
		add(puntuacion);

		Label tiempo = new Label("Movimientos");
		tiempo.setBounds(200, 40, 70, 30);
		add(tiempo);

		listado = new TextArea(basedatos.obtenerListaPuntuaciones());
		listado.setBounds(30, 70, 250, 100);
		add(listado);

		btnaceptar = new Button("Aceptar");
		btnaceptar.setBounds(130, 190, 100, 30);
		add(btnaceptar);
		btnaceptar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object a;
		a = e.getSource();
		if (a.equals(btnaceptar)) {
			this.dispose();
		}
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		this.dispose();
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}
}
