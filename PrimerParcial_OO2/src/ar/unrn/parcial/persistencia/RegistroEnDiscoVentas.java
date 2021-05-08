package ar.unrn.parcial.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ar.unrn.parcial.modelo.RepositorioCombustibles;
import ar.unrn.parcial.modelo.RepositorioVentas;
import ar.unrn.parcial.modelo.Venta;

public class RegistroEnDiscoVentas implements RepositorioVentas {

	private RepositorioCombustibles repositorioCombustibles;

	public RegistroEnDiscoVentas(RepositorioCombustibles repositorioCombustibles) {
		this.repositorioCombustibles = repositorioCombustibles;
	}

	@Override
	public void registrarVenta(Venta venta) {
		try {
			String registro = venta.toString() + "\r";
			Files.write(Paths.get("C:/Users/Rodrigo/ventas.txt"), registro.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo persistir", e);
		}
	}

	@Override
	public List<Venta> obtenerVentas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		File archivo = null;
		FileReader fr = null;
		String unRegistro = null;
		BufferedReader br = null;
		List<Venta> ventas = new ArrayList<Venta>();
		Venta venta = null;
		String[] parts;
		LocalDateTime fechaVenta = null;
		archivo = new File("C:/Users/Rodrigo/ventas.txt");
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			while ((unRegistro = br.readLine()) != null) {
				parts = unRegistro.split(";");
				fechaVenta = crearFecha(parts[0]);
				if (fechaVenta.isAfter(fechaInicio) && fechaVenta.isBefore(fechaFin)) {
					venta = new Venta(construirFecha(parts[0]), Double.valueOf(parts[1]),
							repositorioCombustibles.obtenerCombustible(parts[2]));
					ventas.add(venta);
				}
			}
			br.close();
			fr.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException("Archivo no encontrado", e);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo leer el archivo", e);
		}

		return ventas;

	}

	private LocalDateTime construirFecha(String fecha) {
		String[] partsFechaYHora = fecha.split("-");
		String[] partsFecha = partsFechaYHora[0].split("/");
		String[] partsHora = partsFechaYHora[1].split(":");

		LocalDateTime fechaReconstruida = LocalDateTime.of(Integer.valueOf(partsFecha[0]),
				Integer.valueOf(partsFecha[1]), Integer.valueOf(partsFecha[2]), Integer.valueOf(partsHora[0]),
				Integer.valueOf(partsHora[1]), Integer.valueOf(partsHora[2]));
		return fechaReconstruida;
	}

	private LocalDateTime crearFecha(String fecha) {
		String[] partsFechaYHora = fecha.split("-");
		String[] partsFecha = partsFechaYHora[0].split("/");
		String[] partsHora = partsFechaYHora[1].split(":");
		LocalDateTime fechaCreada = null;
		try {
			fechaCreada = LocalDateTime.of(convertirAnumero(partsFecha[2]), convertirAnumero(partsFecha[1]),
					convertirAnumero(partsFecha[0]), convertirAnumero(partsHora[0]), convertirAnumero(partsHora[1]));
		} catch (DateTimeException e) {
			JOptionPane.showInternalMessageDialog(null, "La fecha ingresada no es valida, revisela por favor");
		}
		return fechaCreada;
	}

	private int convertirAnumero(String numero) {
		return Integer.valueOf(numero);
	}

}
