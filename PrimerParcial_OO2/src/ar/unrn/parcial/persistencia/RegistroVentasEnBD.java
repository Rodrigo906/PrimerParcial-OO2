package ar.unrn.parcial.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.unrn.parcial.modelo.RepositorioCombustibles;
import ar.unrn.parcial.modelo.RepositorioVentas;
import ar.unrn.parcial.modelo.Venta;

public class RegistroVentasEnBD implements RepositorioVentas {

	private Connection dbConn;
	private RepositorioCombustibles repositorioCombustible;
	private String url;
	private String user;
	private String password;

	public RegistroVentasEnBD(RepositorioCombustibles repositorioCombustible, String url, String user,
			String password) {
		this.repositorioCombustible = repositorioCombustible;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	private Timestamp convertirFechaATimestamp(LocalDateTime localDateTime) {
		Date fecha = Timestamp.valueOf(localDateTime);
		Timestamp tfechaInferior = new Timestamp(fecha.getTime());
		return tfechaInferior;
	}

	private LocalDateTime convertirFechaALocalDate(Timestamp timestamp) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date fecha = new Date(timestamp.getTime());
		Instant instant = fecha.toInstant();
		LocalDateTime fechaConvertida = instant.atZone(defaultZoneId).toLocalDateTime();
		return fechaConvertida;
	}

	@Override
	public void registrarVenta(Venta venta) {
		setupBaseDeDatos();
		PreparedStatement st = null;
		Timestamp tfecha = convertirFechaATimestamp(venta.obtenerFechaVenta());
		try {
			st = dbConn.prepareStatement(
					"insert into ventas (fecha_venta, litros_cargados, monto_facturado, combustible_cargado) values(?,?,?,?)");
			st.setTimestamp(1, tfecha);
			st.setDouble(2, venta.obtenerLitrosCargados());
			st.setDouble(3, venta.obtenerMonto());
			st.setString(4, venta.obtenerCombustibleCargado());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error al persistir", e);
		}

	}

	@Override
	public List<Venta> obtenerVentas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		setupBaseDeDatos();
		PreparedStatement st = null;
		List<Venta> ventas = new ArrayList<Venta>();
		Timestamp tfechaInferior = convertirFechaATimestamp(fechaInicio);
		Timestamp tfechaSuperior = convertirFechaATimestamp(fechaFin);
		try {
			st = dbConn.prepareStatement("SELECT * FROM ventas WHERE fecha_venta BETWEEN ? AND ?");
			st.setTimestamp(1, tfechaInferior);
			st.setTimestamp(2, tfechaSuperior);

			ResultSet resulS = st.executeQuery();
			while (resulS.next()) {
				ventas.add(new Venta(convertirFechaALocalDate(resulS.getTimestamp("fecha_venta")),
						resulS.getDouble("litros_cargados"), resulS.getString("combustible_cargado"),
						resulS.getDouble("monto_facturado")));

			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener ventas", e);
		}

		return ventas;

	}

	private void setupBaseDeDatos() {
		try {
			this.dbConn = DriverManager.getConnection(this.url, this.user, this.password);
		} catch (SQLException e) {
			throw new RuntimeException("Error al conectar con BD", e);
		}

	}

}
