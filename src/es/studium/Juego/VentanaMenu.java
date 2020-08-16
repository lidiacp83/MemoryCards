package es.studium.Juego;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaMenu extends JFrame implements WindowListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private static Dialog d;

	MenuBar mnbMenu = new MenuBar();
	Menu menu = new Menu("Menu Principal");
	MenuItem nuevaPartida = new MenuItem("Nueva Partida");
	MenuItem jugadores = new MenuItem("Puntuaciones");
	MenuItem ayuda = new MenuItem("Ayuda");
	MenuItem salir = new MenuItem("Salir");

	public VentanaMenu() {
		setLayout(new FlowLayout());
		setTitle("Memory Cards");
		setSize(420, 470);
		this.setLocation(300, 200);

		menu.add(nuevaPartida);
		menu.add(jugadores);
		menu.add(ayuda);
		menu.add(salir);

		mnbMenu.add(menu);
		setMenuBar(mnbMenu);

		ImageIcon imagen4 = new ImageIcon("./memoryok.jpg");

		JLabel lblNewLabel = new JLabel(imagen4);
		lblNewLabel.setBounds(51, 54, 397, 186);
		add(lblNewLabel);

		addWindowListener(this);
		nuevaPartida.addActionListener(this);
		jugadores.addActionListener(this);
		ayuda.addActionListener(this);
		salir.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		Object a;
		a = ae.getSource();
		if (a.equals(nuevaPartida)) {
			Login loguear = new Login();
			loguear.setSize(400, 180);
			loguear.setLocation(300, 200);
			loguear.setVisible(true);
		}

		if (a.equals(jugadores)) {
			Puntuaciones puntuaciones = new Puntuaciones();
			puntuaciones.setSize(330, 250);
			puntuaciones.setLocation(300, 200);
			puntuaciones.setVisible(true);
		}

		if (a.equals(ayuda)) {
			try {
				Runtime.getRuntime().exec("hh.exe ./Help.chm");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		if (a.equals(salir)) {
			Frame f = new Frame();
			d = new Dialog(f, "Mensaje", true);
			d.setLayout(new FlowLayout());
			Button salir = new Button("Aceptar");
			salir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			Button cancelar = new Button("Cancelar");
			cancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					d.setVisible(false);
				}
			});
			d.add(new Label("¿Deseas salir de la aplicación?"));
			d.add(salir);
			d.add(cancelar);
			d.setSize(260, 120);
			d.setLocation(300, 200);
			d.setVisible(true);
		}
	}

	public void windowActivated(WindowEvent we) {
	}

	public void windowClosed(WindowEvent we) {
	}

	public void windowClosing(WindowEvent we) {
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent we) {
	}

	public void windowDeiconified(WindowEvent we) {
	}

	public void windowIconified(WindowEvent we) {
	}

	public void windowOpened(WindowEvent we) {
	}
}