//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.util.*;

/**
 * Implementación concreta de la estrategia de cálculo de promedios.
 * Esta clase calcula el promedio simple (aritmético) considerando todas las notas
 * válidas (mayores a 0) registradas en el historial del estudiante.
 */
public class PromedioSimple implements EstrategiaPromedio {
	
	/**
	 * Calcula el promedio general de todas las notas.
	 * Itera sobre la lista completa, suma todas las calificaciones válidas y divide por la cantidad total.
	 * * @param notas Lista de notas del estudiante.
	 * @return El promedio general redondeado a dos decimales, o 0.0 si no hay notas válidas.
	 */
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