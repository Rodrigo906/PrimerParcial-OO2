package ar.unrn.parcial.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ar.unrn.parcial.modelo.Combustible;
import ar.unrn.parcial.modelo.RepositorioCombustibles;
import ar.unrn.parcial.modelo.RepositorioVentas;
import ar.unrn.parcial.modelo.Venta;

public class PantallaDeCargaCombustible extends JFrame {

	private JTextField textFieldLitrosCargados;
	private ButtonGroup grupoJradioButtonTipoNafta;
	private JTextField textFieldMontoVenta;

	public PantallaDeCargaCombustible(RepositorioVentas repositorioVentas,
			RepositorioCombustibles repositorioCombustible) {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 415, 268);

		JLabel labelLitrosCargados = new JLabel("Litros cargados:");
		labelLitrosCargados.setBounds(10, 38, 98, 14);
		getContentPane().add(labelLitrosCargados);

		JLabel labelTipoNafta = new JLabel("Tipo nafta:");
		labelTipoNafta.setBounds(10, 80, 79, 14);
		getContentPane().add(labelTipoNafta);

		textFieldLitrosCargados = new JTextField();
		textFieldLitrosCargados.setBounds(132, 35, 86, 20);
		getContentPane().add(textFieldLitrosCargados);
		textFieldLitrosCargados.setColumns(10);

		JRadioButton radioButtonNaftaComun = new JRadioButton("Comun");
		radioButtonNaftaComun.setActionCommand("Nafta comun");
		radioButtonNaftaComun.setBounds(98, 76, 98, 23);
		getContentPane().add(radioButtonNaftaComun);

		JRadioButton radioButtonNaftaSuper = new JRadioButton("Super");
		radioButtonNaftaSuper.setActionCommand("Nafta super");
		radioButtonNaftaSuper.setBounds(207, 76, 105, 23);
		getContentPane().add(radioButtonNaftaSuper);

		crearGrupoJRadioButton(radioButtonNaftaComun, radioButtonNaftaSuper);

		JButton buttonConsultarMonto = new JButton("Consultar monto");
		buttonConsultarMonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textFieldMontoVenta.setText("");
					Venta venta = new Venta(LocalDateTime.now(), Double.valueOf(textFieldLitrosCargados.getText()),
							obtenerCombustibleSeleccionado(repositorioCombustible));
					textFieldMontoVenta.setText(String.valueOf(venta.obtenerMonto()));
				} catch (RuntimeException e1) {
					JOptionPane.showInternalMessageDialog(null, e1.getMessage());
				}
			}
		});
		buttonConsultarMonto.setBounds(10, 181, 140, 23);
		getContentPane().add(buttonConsultarMonto);

		JButton buttonConfirmar = new JButton("Confirmar");
		buttonConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					repositorioVentas.registrarVenta(
							new Venta(LocalDateTime.now(), Double.valueOf(textFieldLitrosCargados.getText()),
									obtenerCombustibleSeleccionado(repositorioCombustible)));
				} catch (RuntimeException e1) {
					JOptionPane.showInternalMessageDialog(null, e1.getMessage());
				}
			}
		});
		buttonConfirmar.setBounds(199, 181, 128, 23);
		getContentPane().add(buttonConfirmar);

		JLabel labelMontoVenta = new JLabel("Monto de venta:");
		labelMontoVenta.setBounds(10, 126, 112, 14);
		getContentPane().add(labelMontoVenta);

		textFieldMontoVenta = new JTextField();
		textFieldMontoVenta.setEditable(false);
		textFieldMontoVenta.setBounds(132, 123, 86, 20);
		getContentPane().add(textFieldMontoVenta);
		textFieldMontoVenta.setColumns(10);

	}

	private void crearGrupoJRadioButton(JRadioButton radio1, JRadioButton radio2) {
		grupoJradioButtonTipoNafta = new ButtonGroup();
		grupoJradioButtonTipoNafta.add(radio1);
		grupoJradioButtonTipoNafta.add(radio2);
		radio1.setSelected(true);
	}

	private Combustible obtenerCombustibleSeleccionado(RepositorioCombustibles repositorioCombustible) {
		String descripcionCombustible = grupoJradioButtonTipoNafta.getSelection().getActionCommand();
		Combustible combustible = repositorioCombustible.obtenerCombustible(descripcionCombustible);
		return combustible;

	}

}
