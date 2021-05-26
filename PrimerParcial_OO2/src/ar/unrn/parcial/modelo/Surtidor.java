package ar.unrn.parcial.modelo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Surtidor extends Sujeto {

	private RepositorioVentas repositorioVentas;
	private Map<String, Combustible> tipoCombustible = new HashMap<String, Combustible>();

	public Surtidor(RepositorioVentas repoVentas, Fecha fecha, List<ObservadorVentas> observadores) {
		this.repositorioVentas = repoVentas;
		this.tipoCombustible.put("comun", new CombustibleComun(70, "Nafta comun", 0.05, fecha));
		this.tipoCombustible.put("super", new CombustibleSuper(90, "Nafta super", 0.1, 0.12, 20, fecha));
		for (ObservadorVentas obs : observadores) {
			this.agregarObservador(obs);
		}
	}

	public double calcularMonto(String cantLitros, String tipoCombustible) {
		Combustible combustible = this.tipoCombustible.get(tipoCombustible);
		return combustible.calcularMontoAPagar(cantLitros);
	}

	public void registrarVenta(String cantLitros, String tipoCombustible, String email) {
		Map<String, String> datos = new HashMap<String, String>();

		double monto = this.calcularMonto(cantLitros, tipoCombustible);
		Venta venta = new Venta(LocalDateTime.now(), Double.valueOf(cantLitros), tipoCombustible, monto);

		repositorioVentas.registrarVenta(venta);
		datos.put("titulo", "Gracias por su compra");
		datos.put("cuerpo", venta.toString());
		datos.put("mailDestinatario", email);

		this.notificar(datos);
	}

}
