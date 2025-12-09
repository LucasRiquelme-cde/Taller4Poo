//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.util.ArrayList;

public class PromedioSoloAprobados implements EstrategiaPromedio {

	@Override
	public double calcular(ArrayList<Nota> notas) {
		if (notas.isEmpty()) return 0.0;
		double suma = 0;
		int contador = 0;
		
		for (Nota n : notas) {
			if (n.getCalificacion() >= 4.0) {
				suma += n.getCalificacion();
				contador++;
			}
		}
		
		if (contador == 0) return 0.0;
		return Math.round((suma / contador) * 100.0) / 100.0;
	}

}
