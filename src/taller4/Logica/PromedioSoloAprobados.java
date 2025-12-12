// Matías Collao / 22.060.152-8 / ICCI
// Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.Logica;

import java.util.ArrayList;

import taller4.Dominio.Nota;

/**
 * Implementación concreta de la estrategia de cálculo de promedios.
 * Esta clase implementa la lógica para calcular el promedio académico considerando
 * únicamente las asignaturas que han sido aprobadas (nota mayor o igual a 4.0).
 */
public class PromedioSoloAprobados implements EstrategiaPromedio {

	/**
	 * Calcula el promedio de las notas aprobadas.
	 * Filtra la lista de notas entregada, sumando solo aquellas con calificación mayor o igual a 4.0.
	 * @param notas Lista de notas del historial del estudiante.
	 * @return El promedio de las notas azules, redondeado a dos decimales. Retorna 0.0 si no hay notas aprobadas.
	 */
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