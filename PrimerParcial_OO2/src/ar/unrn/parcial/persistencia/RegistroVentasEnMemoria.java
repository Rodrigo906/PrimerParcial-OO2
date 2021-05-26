package ar.unrn.parcial.persistencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.parcial.modelo.RepositorioVentas;
import ar.unrn.parcial.modelo.Venta;

public class RegistroVentasEnMemoria implements RepositorioVentas {

	private List<Venta> ventas = new ArrayList<Venta>();

	@Override
	public void registrarVenta(Venta venta) {
		ventas.add(venta);
	}

	@Override
	public List<Venta> obtenerVentas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		List<Venta> ventas = new ArrayList<Venta>();
		for (Venta v : ventas) {
			if (v.obtenerFechaVenta().isAfter(fechaInicio) && v.obtenerFechaVenta().isBefore(fechaFin)) {
				ventas.add(v);
			}
		}
		return ventas;
	}

}
