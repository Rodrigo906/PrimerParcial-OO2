package ar.unrn.parcial.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ar.unrn.parcial.modelo.Combustible;
import ar.unrn.parcial.modelo.CombustibleComun;
import ar.unrn.parcial.modelo.CombustibleSuper;
import ar.unrn.parcial.modelo.RepositorioCombustibles;
import ar.unrn.parcial.variables.FechaImp;

public class RegistroEnDiscoCombustible implements RepositorioCombustibles {

	private String path;

	public RegistroEnDiscoCombustible(String path) {
		this.path = path;
	}

	@Override
	public Combustible obtenerCombustible(String descripcion) {
		File archivo = null;
		FileReader fr = null;
		String unRegistro = null;
		BufferedReader br = null;
		String[] parts;
		archivo = new File(path);
		Combustible combustible = null;
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			while ((unRegistro = br.readLine()) != null) {
				parts = unRegistro.split(";");
				if (parts[1].equals(descripcion)) {
					if (descripcion.equals("Nafta comun")) {
						combustible = new CombustibleComun(Double.valueOf(parts[0]), parts[1], Double.valueOf(parts[2]),
								new FechaImp());
					} else if (descripcion.equals("Nafta super"))
						combustible = new CombustibleSuper(Double.valueOf(parts[0]), parts[1], Double.valueOf(parts[2]),
								Double.valueOf(parts[3]), Double.valueOf(parts[4]), new FechaImp());
				}
			}
			br.close();
			fr.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException("Archivo no encontrado", e);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo leer el archivo", e);
		}

		return combustible;
	}

	@Override
	public void registrarCombustible(Combustible combustible) {
		// TODO Auto-generated method stub

	}

}
