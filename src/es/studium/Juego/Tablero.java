package es.studium.Juego;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

public class Tablero extends Frame implements WindowListener, ActionListener {
	private BaseDatos basedatos = new BaseDatos();
	private static final long serialVersionUID = 1L;
	Button[][] teclas = new Button[4][4];
	int teclasPulsadas[][] = new int[4][4];
	private int[] controlNumeros = new int[8];
	int intentos, intentosTotales;
	String primerNumero;
	String segundoNumero;
	int primeraPosicion;
	int segundaPosicion;
	int parejas = 0;
	Random aleatorio = new Random();
	private static Dialog d;
	String nombrejugador = "";
	Color[] colores = { Color.WHITE, Color.BLUE, Color.GREEN, Color.PINK, Color.YELLOW, Color.RED, Color.MAGENTA,
			Color.LIGHT_GRAY };

	Tablero(String nombrejugador) {
		// Guardamos el nombre del jugador en una variable para más tarde
		this.nombrejugador = nombrejugador;
		setLayout(new GridLayout(4, 4));
		setTitle("Memory Cards");

		// Iniciamos los botones del tablero y el array para el control de numeros
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				teclas[i][j] = new Button();
				teclas[i][j].setBackground(Color.DARK_GRAY);
				teclas[i][j].addActionListener(this);
				add(teclas[i][j]);
			}
		}
		addWindowListener(this);

		// Cargamos los números aleatorios en los botones del tablero
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// Obtenemos un numero aleatorio
				int numero = aleatorio.nextInt(8);
				// Comprobamos si el número se ha introducido ya 2 veces
				if (controlNumeros[numero] < 2) {
					teclas[i][j].setName(String.valueOf(numero));
					// Sumamos 1 a la posicion del numero aleatorio que ha salido para llevar la
					// cuenta
					controlNumeros[numero]++;
				} else {
					// Si ya se ha introducido 2 veces hay que buscar otro para el mismo boton
					j--;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (e.getSource() == teclas[i][j]) {
					// Comprobamos si la tecla ya estaba pulsada
					if (teclasPulsadas[i][j] == 0) { // 0: No esta pulsada, 1: Si esta pulsada
						switch (intentos) {
						case 0: // Si es la primera tecla que pulsa
							// Cogemos el color del array de colores segun el numero aleatorio que haya en
							// la tecla que se ha pulsado
							teclas[i][j].setBackground(colores[Integer.parseInt(teclas[i][j].getName())]);
							// Marcamos la tecla como pulsada
							teclasPulsadas[i][j] = 1;
							// Guardamos el numero de la tecla
							primerNumero = teclas[i][j].getName();
							// Guardamos la posicion de la casilla
							primeraPosicion = i;
							segundaPosicion = j;
							// Sumamos 1 intento
							intentos++;
							// Sumamos un intento al total
							intentosTotales++;
							break;
						case 1: // Si es la segunda tecla que pulsa
							// Cogemos el color del array de colores segun el numero aleatorio que haya en
							// la tecla que se ha pulsado
							teclas[i][j].setBackground(colores[Integer.parseInt(teclas[i][j].getName())]);
							// Marcamos la tecla como pulsada
							teclasPulsadas[i][j] = 1;
							// Guardamos el numero de la tecla
							segundoNumero = teclas[i][j].getName();
							// Si los numeros no son iguales
							if (!primerNumero.equals(segundoNumero)) {
								// Esperamos un tiempo para que se muestre el segundo color
								try {
									Thread.sleep(350);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								// Buscamos las teclas que han sido pulsadas y las volvemos de color blanco
								for (int k = 0; k < 4; k++) {
									for (int l = 0; l < 4; l++) {
										if (teclas[k][l].getName().equals(primerNumero)
												|| teclas[k][l].getName().equals(segundoNumero)) {
											teclas[k][l].setBackground(Color.DARK_GRAY);
										}
									}
								}
								// Volvemos a marcar las teclas como NO pulsadas
								teclasPulsadas[primeraPosicion][segundaPosicion] = 0;
								teclasPulsadas[i][j] = 0;
							} else {
								// Si los numeros son iguales sumamos 1 pareja econtrada más
								parejas++;
							}
							// Volvemos los intentos a cero
							intentos = 0;
							// Sumanos un intento al total
							intentosTotales++;
							break;
						}
					}
				}
			}
		}

		// Si ha encontrado las 8 parejas
		if (parejas == 8) {
			// Obtenemos la lista de jugadores
			String[][] listado = basedatos.obtenerJugadores();
			// Buscamos un jugador con la puntuacion más baja y grabamos el nuevo
			for (int i = 0; i < 10; i++) {
				if (Integer.parseInt(listado[i][2]) > intentosTotales) {
					basedatos.actualizarPuntuaciones(listado[i][0], nombrejugador, String.valueOf(intentosTotales));
					break;
				}
			}

			// Mostramos el mensaje de fin de juego
			Frame f = new Frame();
			f.setBackground(Color.white);
			d = new Dialog(f, "Mensaje", true);
			d.setLayout(null);

			Label mensaje = new Label("Bien!!! Lo has conseguido en " + intentosTotales + " movimientos!!!");
			mensaje.setBounds(30, 60, 300, 30);
			d.add(mensaje);

			Button aceptar = new Button("Aceptar");
			aceptar.setBounds(110, 100, 90, 30);
			aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					d.setVisible(false);
					dispose();
				}
			});
			d.add(aceptar);
			d.setSize(320, 170);
			d.setLocation(300, 200);
			d.setVisible(true);
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
