package ar.unrn.parcial.modelo;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositorioVentas {

	public void registrarVenta(Venta venta);

	public List<Venta> obtenerVentas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
