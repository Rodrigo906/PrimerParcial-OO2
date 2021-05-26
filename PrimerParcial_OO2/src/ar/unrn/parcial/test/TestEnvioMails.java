package ar.unrn.parcial.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.unrn.parcial.modelo.Fecha;
import ar.unrn.parcial.modelo.Surtidor;
import ar.unrn.parcial.observadores.MailTrapMask;
import ar.unrn.parcial.persistencia.RegistroVentasEnMemoria;
import ar.unrn.parcial.variables.FechaImp;

public class TestEnvioMails {

	@Test
	public void enviarMailCliente() {
		MailTrapMask obsVentas = new MailTrapMask();
		Fecha fecha = new FechaImp();
		Surtidor surtidor = new Surtidor(new RegistroVentasEnMemoria(), fecha, List.of(obsVentas));

		surtidor.registrarVenta("20", "comun", "rodrigo@gmail.com");

		Assert.assertEquals("mail enviado", obsVentas.contenido());

	}

}
