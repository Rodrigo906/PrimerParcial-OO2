package ar.unrn.parcial.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import ar.unrn.parcial.modelo.RepositorioVentas;
import ar.unrn.parcial.modelo.Venta;

public class PantallaDeListadoVentas extends JFrame {

	private JScrollPane panel_tabla;
	private DefaultTableModel modeloTabla;
	private JFormattedTextField fechaInicio;
	private JFormattedTextField fechaFin;
	private JTable tabla;

	public PantallaDeListadoVentas(RepositorioVentas repositorioVentas) {

		String[] titulos = { "Fecha de Venta", "Litros Cargados", "Monto Facturado", "Combustible Cargado" };

		setBounds(10, 10, 550, 380);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel labelFechaInicio = new JLabel("Fecha inicio:");
		labelFechaInicio.setBounds(10, 256, 89, 14);
		getContentPane().add(labelFechaInicio);

		JLabel labelFechaFin = new JLabel("Fecha fin:");
		labelFechaFin.setBounds(245, 256, 68, 14);
		getContentPane().add(labelFechaFin);

		fechaInicio = new JFormattedTextField();
		fechaInicio.setBounds(91, 253, 98, 20);
		fechaInicio.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(fechaInicio);

		fechaFin = new JFormattedTextField();
		fechaFin.setBounds(335, 253, 98, 20);
		fechaFin.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(fechaFin);

		try {
			DefaultFormatterFactory formatoFecha = new DefaultFormatterFactory(new MaskFormatter("##/##/####-##:##"));
			fechaInicio.setFormatterFactory(formatoFecha);
			fechaFin.setFormatterFactory(formatoFecha);
		} catch (ParseException e) {
			JOptionPane.showInternalMessageDialog(null, "La fecha ingresada no es valida, revisela por favor");
		}

		tabla = new JTable();
		modeloTabla = new DefaultTableModel(new Object[][] {}, titulos);

		JButton buttonConsultar = new JButton("Consultar");
		buttonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					List<Venta> ventas = repositorioVentas.obtenerVentas(crearFecha(fechaInicio.getText()),
							crearFecha(fechaFin.getText()));

					String[] parts = null;
					for (Venta v : ventas) {
						parts = v.toString().split(";");
						modeloTabla.addRow(new Object[] { parts[0], parts[1], parts[2], parts[3] });
					}
					tabla.setModel(modeloTabla);

					panel_tabla = new JScrollPane(tabla);
					panel_tabla.setBounds(10, 11, 514, 206);
					getContentPane().add(panel_tabla);
				} catch (RuntimeException e1) {
					JOptionPane.showInternalMessageDialog(null, e1.getMessage());
				}
			}
		});
		buttonConsultar.setBounds(387, 300, 122, 23);
		getContentPane().add(buttonConsultar);

	}

	private LocalDateTime crearFecha(String fecha) {
		String[] partsFechaYHora = fecha.split("-");
		String[] partsFecha = partsFechaYHora[0].split("/");
		String[] partsHora = partsFechaYHora[1].split(":");
		LocalDateTime fechaCreada = null;
		try {
			fechaCreada = LocalDateTime.of(convertirAnumero(partsFecha[2]), convertirAnumero(partsFecha[1]),
					convertirAnumero(partsFecha[0]), convertirAnumero(partsHora[0]), convertirAnumero(partsHora[1]));
		} catch (DateTimeException e) {
			JOptionPane.showInternalMessageDialog(null, "La fecha ingresada no es valida, revisela por favor");
		}
		return fechaCreada;
	}

	private int convertirAnumero(String numero) {
		return Integer.valueOf(numero);
	}

}
