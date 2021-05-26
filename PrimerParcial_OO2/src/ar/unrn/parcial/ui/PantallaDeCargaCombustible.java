package ar.unrn.parcial.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ar.unrn.parcial.modelo.Fecha;
import ar.unrn.parcial.modelo.RepositorioCombustibles;
import ar.unrn.parcial.modelo.RepositorioVentas;
import ar.unrn.parcial.modelo.Surtidor;
import ar.unrn.parcial.observadores.MailTrap;

public class PantallaDeCargaCombustible extends JFrame {

	private JTextField textFieldLitrosCargados;
	private ButtonGroup grupoJradioButtonTipoNafta;
	private JTextField textFieldMontoVenta;
	private Surtidor surtidor;
	private JTextField textFieldEmail;

	public PantallaDeCargaCombustible(RepositorioVentas repositorioVentas,
			RepositorioCombustibles repositorioCombustible, Fecha fecha) {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 415, 302);

		JLabel labelLitrosCargados = new JLabel("Litros cargados:");
		labelLitrosCargados.setBounds(10, 38, 98, 14);
		getContentPane().add(labelLitrosCargados);

		JLabel labelTipoNafta = new JLabel("Tipo nafta:");
		labelTipoNafta.setBounds(10, 133, 79, 14);
		getContentPane().add(labelTipoNafta);

		textFieldLitrosCargados = new JTextField();
		textFieldLitrosCargados.setBounds(132, 35, 86, 20);
		getContentPane().add(textFieldLitrosCargados);
		textFieldLitrosCargados.setColumns(10);

		JRadioButton radioButtonNaftaComun = new JRadioButton("Comun");
		radioButtonNaftaComun.setActionCommand("comun");
		radioButtonNaftaComun.setBounds(98, 129, 98, 23);
		getContentPane().add(radioButtonNaftaComun);

		JRadioButton radioButtonNaftaSuper = new JRadioButton("Super");
		radioButtonNaftaSuper.setActionCommand("super");
		radioButtonNaftaSuper.setBounds(210, 129, 105, 23);
		getContentPane().add(radioButtonNaftaSuper);

		crearGrupoJRadioButton(radioButtonNaftaComun, radioButtonNaftaSuper);
		surtidor = new Surtidor(repositorioVentas, fecha, List.of(new MailTrap()));

		JButton buttonConsultarMonto = new JButton("Consultar monto");
		buttonConsultarMonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textFieldMontoVenta.setText("");
					Double resultado = surtidor.calcularMonto(textFieldLitrosCargados.getText(),
							obtenerCombustibleSeleccionado());
					textFieldMontoVenta.setText(String.valueOf(resultado));

				} catch (RuntimeException e1) {
					JOptionPane.showInternalMessageDialog(null, e1.getMessage());
				}

			}
		});
		buttonConsultarMonto.setBounds(10, 229, 140, 23);
		getContentPane().add(buttonConsultarMonto);

		JButton buttonConfirmar = new JButton("Confirmar");
		buttonConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					surtidor.registrarVenta(textFieldLitrosCargados.getText(), obtenerCombustibleSeleccionado(),
							textFieldEmail.getText());
					JOptionPane.showInternalMessageDialog(null, "Venta exitosa");
				} catch (RuntimeException e1) {
					JOptionPane.showInternalMessageDialog(null, e1.getMessage());
				}
			}
		});
		buttonConfirmar.setBounds(250, 229, 128, 23);
		getContentPane().add(buttonConfirmar);

		JLabel labelMontoVenta = new JLabel("Monto de venta:");
		labelMontoVenta.setBounds(10, 176, 112, 14);
		getContentPane().add(labelMontoVenta);

		textFieldMontoVenta = new JTextField();
		textFieldMontoVenta.setEditable(false);
		textFieldMontoVenta.setBounds(132, 173, 86, 20);
		getContentPane().add(textFieldMontoVenta);
		textFieldMontoVenta.setColumns(10);

		JLabel labelMail = new JLabel("Email: ");
		labelMail.setBounds(10, 82, 79, 14);
		getContentPane().add(labelMail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(132, 79, 140, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

	}

	private void crearGrupoJRadioButton(JRadioButton radio1, JRadioButton radio2) {
		grupoJradioButtonTipoNafta = new ButtonGroup();
		grupoJradioButtonTipoNafta.add(radio1);
		grupoJradioButtonTipoNafta.add(radio2);
		radio1.setSelected(true);
	}

	private String obtenerCombustibleSeleccionado() {
		return grupoJradioButtonTipoNafta.getSelection().getActionCommand();
	}
}
