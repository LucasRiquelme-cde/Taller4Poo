// Matías Collao / 22.060.152-8 / ICCI
// Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.Logica;

import java.util.ArrayList;

import taller4.Dominio.Nota;
import taller4.Dominio.*;

/**
 * Interfaz que define la estrategia para el cálculo de promedios.
 * Parte de la implementación del patrón de diseño Strategy.
 * Permite intercambiar diferentes algoritmos para calcular el rendimiento académico.
 */
public interface EstrategiaPromedio {
	
	/**
	 * Calcula un promedio numérico basado en una lista de notas proporcionada.
	 * @param notas Lista de objetos Nota sobre la cual se realizará el cálculo.
	 * @return El valor del promedio calculado como un double.
	 */
	public double calcular(ArrayList<Nota> notas);
}