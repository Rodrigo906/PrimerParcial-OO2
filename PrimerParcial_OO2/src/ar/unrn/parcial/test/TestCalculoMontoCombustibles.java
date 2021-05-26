package ar.unrn.parcial.test;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.parcial.modelo.Fecha;
import ar.unrn.parcial.modelo.Surtidor;
import ar.unrn.parcial.observadores.MailTrapMask;
import ar.unrn.parcial.persistencia.RegistroVentasEnMemoria;

public class TestCalculoMontoCombustibles {

	@Test
	public void calcularMontoCombustibleSuperSabadoSuperandoLimiteDeDescuento() {
		Fecha fecha = new Fecha() {
			@Override
			public LocalDateTime hoy() {
				return LocalDateTime.of(2021, 5, 8, 9, 0);
			}
		};
		Surtidor surtidor = new Surtidor(new RegistroVentasEnMemoria(), fecha, List.of(new MailTrapMask()));
		Double resultado = surtidor.calcularMonto("40", "super");
		Assert.assertEquals(3168d, resultado, 1.0f);
	}

	@Test
	public void calcularMontoCombustibleComunEnMañana() {
		Fecha fecha = new Fecha() {
			@Override
			public LocalDateTime hoy() {
				return LocalDateTime.of(2021, 5, 12, 9, 0);
			}
		};
		Surtidor surtidor = new Surtidor(new RegistroVentasEnMemoria(), fecha, List.of(new MailTrapMask()));
		Double resultado = surtidor.calcularMonto("40", "comun");
		Assert.assertEquals(2660d, resultado, 1.0f);
	}

	@Test
	public void calcularMontoCombustibleSuperSemanaSinSuperarLimiteDeDescuento() {
		Fecha fecha = new Fecha() {
			@Override
			public LocalDateTime hoy() {
				return LocalDateTime.of(2021, 5, 12, 9, 0);
			}
		};
		Surtidor surtidor = new Surtidor(new RegistroVentasEnMemoria(), fecha, List.of(new MailTrapMask()));
		Double resultado = surtidor.calcularMonto("40", "super");
		Assert.assertEquals(3600d, resultado, 1.0f);
	}

	@Test
	public void calcularMontoCombustibleComunRestoDelDia() {
		Fecha fecha = new Fecha() {
			@Override
			public LocalDateTime hoy() {
				return LocalDateTime.of(2021, 5, 12, 12, 0);
			}
		};
		Surtidor surtidor = new Surtidor(new RegistroVentasEnMemoria(), fecha, List.of(new MailTrapMask()));
		Double resultado = surtidor.calcularMonto("40", "comun");
		Assert.assertEquals(2800d, resultado, 1.0f);
	}

}
