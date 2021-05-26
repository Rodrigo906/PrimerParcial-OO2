package ar.unrn.parcial.main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.parcial.modelo.Combustible;
import ar.unrn.parcial.modelo.CombustibleComun;
import ar.unrn.parcial.modelo.CombustibleSuper;
import ar.unrn.parcial.modelo.RepositorioCombustibles;
import ar.unrn.parcial.modelo.RepositorioVentas;
import ar.unrn.parcial.modelo.Venta;
import ar.unrn.parcial.persistencia.RegistroEnDiscoCombustible;
import ar.unrn.parcial.persistencia.RegistroEnDiscoVentas;
import ar.unrn.parcial.variables.FechaImp;

public class MainEnDisco {

	public static void main(String[] args) {

		Combustible combustible1 = new CombustibleComun(70, "Nafta comun", 0.05, new FechaImp());
		Combustible combustible2 = new CombustibleSuper(90, "Nafta super", 0.1, 0.12, 20, new FechaImp());

		Venta venta1 = new Venta(LocalDateTime.now(), 30, combustible2.obtenerDescripcion(), 0);
		List<Venta> ventas = new ArrayList<Venta>();

		RepositorioCombustibles repoCombustible = new RegistroEnDiscoCombustible("C:/Users/Rodrigo/combustibles.txt");
		RepositorioVentas repoVentas = new RegistroEnDiscoVentas(repoCombustible, "C:/Users/Rodrigo/ventas.txt");
		repoVentas.registrarVenta(venta1);
		ventas = repoVentas.obtenerVentas(LocalDateTime.of(2021, 05, 8, 00, 00), LocalDateTime.of(2021, 05, 8, 23, 00));
		for (Venta v : ventas) {
			System.out.println(v.toString());
		}

	}

}
