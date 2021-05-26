package ar.unrn.parcial.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ar.unrn.parcial.modelo.Fecha;
import ar.unrn.parcial.modelo.RepositorioCombustibles;
import ar.unrn.parcial.modelo.RepositorioVentas;
import ar.unrn.parcial.persistencia.RegistroCombustibleEnMemoria;
import ar.unrn.parcial.persistencia.RegistroVentasEnMemoria;
import ar.unrn.parcial.variables.FechaImp;

public class PantallaPrincipal extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				RepositorioCombustibles repositorioCombustible = new RegistroCombustibleEnMemoria();
				/*
				 * RepositorioVentas repositorioVentas = new
				 * RegistroVentasEnBD(repositorioCombustible,
				 * "jdbc:mysql://localhost:3306/estacionservicioypzw", "root", "");
				 */

				RepositorioVentas repositorioVentas = new RegistroVentasEnMemoria();
				Fecha fecha = new FechaImp();
				PantallaPrincipal pantPrincipal = new PantallaPrincipal(repositorioVentas, repositorioCombustible,
						fecha);
				pantPrincipal.setVisible(true);
			}
		});
	}

	public PantallaPrincipal(RepositorioVentas repositorioVentas, RepositorioCombustibles repositorioCombustible,
			Fecha fecha) {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 415, 300);

		JLabel jlabel_CargaCombustible = new JLabel("Carga de combustible");
		jlabel_CargaCombustible.setBounds(10, 94, 133, 14);
		getContentPane().add(jlabel_CargaCombustible);

		JLabel jabel_listadoVentas = new JLabel("Mostrar listado de Ventas");
		jabel_listadoVentas.setBounds(10, 147, 133, 14);
		getContentPane().add(jabel_listadoVentas);

		JButton btnFormularioDeVenta = new JButton("Formulario de venta");
		btnFormularioDeVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PantallaDeCargaCombustible pantallaCarga = new PantallaDeCargaCombustible(repositorioVentas,
						repositorioCombustible, fecha);
				pantallaCarga.setVisible(true);
			}
		});
		btnFormularioDeVenta.setBounds(170, 90, 154, 23);
		getContentPane().add(btnFormularioDeVenta);

		JButton btnMostrarListado = new JButton("Mostrar listado");
		btnMostrarListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaDeListadoVentas pantallaListadoVentas = new PantallaDeListadoVentas(repositorioVentas);
				pantallaListadoVentas.setVisible(true);
			}
		});
		btnMostrarListado.setBounds(170, 143, 154, 23);
		getContentPane().add(btnMostrarListado);
	}
}
