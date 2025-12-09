package taller4;

import java.util.*;

public class PromedioSimple implements EstrategiaPromedio {
	
	public double calcular(ArrayList<Nota> notas) {
		if (notas.isEmpty()) return 0.0;
		double suma = 0;
		int contador = 0;
		
		for (Nota n : notas) {
			if (n.getCalificacion() > 0) {
				suma += n.getCalificacion();
				contador++;
			}
		}
		
		if (contador == 0) return 0.0;
		return Math.round((suma / contador) * 100.0) / 100.0;
	}

}