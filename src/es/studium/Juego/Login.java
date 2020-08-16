package es.studium.Juego;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Login extends Frame implements WindowListener {
	private static final long serialVersionUID = 1L;

	String nombreJugador;
	Label Bienve;
	TextField jugador;

	public Login() {

		setLayout(null);
		// setLocation nos permitirá indicar donde se posicionará la ventana al abrirse
		this.setLocation(320, 230);
		setTitle("Memory Cards");

		// Se crea el label y la caja de texto donde se colocará el jugador
		Bienve = new Label("¡Bienvenido a Memory Cards!, elija un nombre de jugador ");
		Bienve.setBounds(30, 40, 350, 30);
		add(Bienve);

		jugador = new TextField("");
		jugador.setBounds(60, 70, 250, 30);
		add(jugador);

		// Se crea el boton y también se define el comportamiento que tendrá, en este
		// caso invocaremos el método crear partida
		Button ingresar = new Button("Jugar");
		ingresar.setBounds(120, 120, 150, 30);
		ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				crearPartida();
			}
		});
		add(ingresar);

		addWindowListener(this);
	}

	public void crearPartida() {
		nombreJugador = jugador.getText();
		Tablero nuevaPartida = new Tablero(nombreJugador);
		nuevaPartida.setSize(400, 400);
		nuevaPartida.setLocation(300, 200);
		nuevaPartida.setResizable(false);
		nuevaPartida.setVisible(true);
		this.setVisible(false);
	}

	public void windowClosing(WindowEvent we) {
		this.dispose();
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}
}
