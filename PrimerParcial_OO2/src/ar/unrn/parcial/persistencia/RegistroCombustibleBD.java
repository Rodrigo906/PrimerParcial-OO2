package ar.unrn.parcial.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.unrn.parcial.modelo.Combustible;
import ar.unrn.parcial.modelo.CombustibleComun;
import ar.unrn.parcial.modelo.CombustibleSuper;
import ar.unrn.parcial.modelo.RepositorioCombustibles;
import ar.unrn.parcial.variables.FechaImp;

public class RegistroCombustibleBD implements RepositorioCombustibles {

	private Connection dbConn;

	@Override
	public Combustible obtenerCombustible(String descripcion) {
		setupBaseDeDatos();
		PreparedStatement st = null;
		Combustible combustible = null;
		try {
			st = dbConn.prepareStatement("SELECT * FROM combustibles WHERE descripcion = ?");
			st.setString(1, descripcion);
			ResultSet resulS = st.executeQuery();
			while (resulS.next()) {
				if (resulS.getString("descripcion").equals("Nafta comun")) {
					combustible = new CombustibleComun(resulS.getDouble("precioxlitro"),
							resulS.getString("descripcion"), resulS.getDouble("descuento_mañana"), new FechaImp());
				} else if (resulS.getString("descripcion").equals("Nafta super")) {
					combustible = new CombustibleSuper(resulS.getDouble("precioxlitro"),
							resulS.getString("descripcion"), resulS.getDouble("descuento_domingo"),
							resulS.getDouble("descuento_sabado"), resulS.getDouble("min_litros_para_descuento"),
							new FechaImp());
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener combustible", e);
		}

		return combustible;
	}

	private void setupBaseDeDatos() {
		String url = "jdbc:mysql://localhost:3306/estacionservicioypzw";
		String user = "root";
		String password = "";
		try {
			this.dbConn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException("Error al persistir", e);
		}

	}

	@Override
	public void registrarCombustible(Combustible combustible) {
		// TODO Auto-generated method stub

	}
}
