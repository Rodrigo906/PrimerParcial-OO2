package ar.unrn.parcial.test;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.parcial.modelo.Combustible;
import ar.unrn.parcial.modelo.CombustibleComun;
import ar.unrn.parcial.modelo.CombustibleSuper;
import ar.unrn.parcial.modelo.Venta;

public class TestCalculoMontoCombustibles {

	@Test
	public void calcularMontoCombustibleSuperSabadoSuperandoLimiteDeDescuento() {
		Combustible combustible2 = new CombustibleSuper(90, "Nafta super", 0.1, 0.12, 20);
		Venta venta1 = new Venta(LocalDateTime.now(), 40, combustible2);
		Assert.assertEquals(Double.valueOf(3168), (Double) venta1.obtenerMonto());
	}

	@Test
	public void calcularMontoCombustibleComunEnMañana() {
		Combustible combustible1 = new CombustibleComun(70, "Nafta comun", 0.05);
		Venta venta1 = new Venta(LocalDateTime.now(), 40, combustible1);
		Assert.assertEquals(Double.valueOf(2660), (Double) venta1.obtenerMonto());
	}

	@Test
	public void calcularMontoCombustibleSuperSemanaSinSuperarLimiteDeDescuento() {
		Combustible combustible2 = new CombustibleSuper(90, "Nafta super", 0.1, 0.12, 20);
		Venta venta1 = new Venta(LocalDateTime.now(), 40, combustible2);
		Assert.assertEquals(Double.valueOf(3600), (Double) venta1.obtenerMonto());
	}

	@Test
	public void calcularMontoCombustibleComunRestoDelDia() {
		Combustible combustible1 = new CombustibleComun(70, "Nafta comun", 0.05);
		Venta venta1 = new Venta(LocalDateTime.now(), 40, combustible1);
		Assert.assertEquals(Double.valueOf(2800), (Double) venta1.obtenerMonto());
	}

}
