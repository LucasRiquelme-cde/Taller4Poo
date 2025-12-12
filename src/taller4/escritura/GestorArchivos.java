// Matías Collao / 22.060.152-8 / ICCI
// Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.escritura; 

import java.io.*;
import java.util.*;
import taller4.modelo.*; 

/**
 * Clase perteneciente a la capa de Acceso a Datos (DAO).
 * <p>
 * Su responsabilidad es gestionar exclusivamente la persistencia física de la información.
 * Se encarga de leer los datos desde los archivos de texto (.txt) para convertirlos en objetos
 * y de escribir los cambios realizados en memoria de vuelta a los archivos.
 * </p>
 */
public class GestorArchivos {

    /**
     * Lee el archivo de texto de estudiantes y lo convierte en una lista de objetos.
     * @param archivo Ruta o nombre del archivo a leer.
     * @return ArrayList con los estudiantes cargados.
     */
    public ArrayList<Estudiante> leerEstudiantes(String archivo) {
        ArrayList<Estudiante> lista = new ArrayList<>();
        try (Scanner s = new Scanner(new File(archivo))) {
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                lista.add(new Estudiante(p[0], p[1], p[2], p[3], p[4], p[5]));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + archivo);
        }
        return lista;
    }

    /**
     * Lee el archivo de usuarios y utiliza la fábrica para instanciar el rol correcto.
     * @param archivo Ruta del archivo de usuarios.
     * @param fu      Instancia de FactoryUsuario para la creación de objetos.
     * @return ArrayList con los usuarios (Administradores y Coordinadores) cargados.
     */
    public ArrayList<Usuario> leerUsuarios(String archivo, FactoryUsuario fu) {
        ArrayList<Usuario> lista = new ArrayList<>();
        try (Scanner s = new Scanner(new File(archivo))) {
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                
                String nombre = p[0];
                String pass = p[1];
                String rol = p[2];
                String info = (p.length > 3) ? p[3] : ""; 
                
                Usuario u = fu.getUsuario(nombre, pass, rol, info);
                if (u != null) {
                    lista.add(u);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + archivo);
        }
        return lista;
    }

    /**
     * Lee el archivo de cursos y lo convierte en una lista de objetos Curso.
     * @param archivo Ruta del archivo de cursos.
     * @return ArrayList de cursos disponibles.
     */
    public ArrayList<Curso> leerCursos(String archivo) {
        ArrayList<Curso> lista = new ArrayList<>();
        try (Scanner s = new Scanner(new File(archivo))) {
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                String prereq = (p.length == 6) ? p[5] : "";
                lista.add(new Curso(p[0], p[1], p[4], prereq, Integer.parseInt(p[2]), Integer.parseInt(p[3])));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + archivo);
        }
        return lista;
    }

    /**
     * Lee el archivo de certificaciones y carga los datos básicos.
     * @param archivo Ruta del archivo de certificaciones.
     * @return ArrayList de certificaciones.
     */
    public ArrayList<Certificacion> leerCertificaciones(String archivo) {
        ArrayList<Certificacion> lista = new ArrayList<>();
        try (Scanner s = new Scanner(new File(archivo))) {
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                lista.add(new Certificacion(p[0], p[1], p[2], p[3], p[4]));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + archivo);
        }
        return lista;
    }

    /**
     * Escribe un nuevo estudiante al final del archivo de texto (modo append).
     * @param e       Objeto Estudiante a guardar.
     * @param archivo Ruta del archivo de destino.
     */
    public void guardarEstudiante(Estudiante e, String archivo) {
        try (FileWriter fw = new FileWriter(archivo, true);
             PrintWriter pw = new PrintWriter(fw)) {
            String linea = e.getRut() + ";" + e.getNombre() + ";" + e.getCarrera() + ";" +
                           e.getSemestre() + ";" + e.getCorreo() + ";" + e.getContraseña();
            pw.append("\n" + linea);
        } catch (IOException ex) {
            System.err.println("Error guardando estudiante: " + ex.getMessage());
        }
    }

    /**
     * Escribe un nuevo usuario del staff al final del archivo de texto.
     * @param u       Objeto Usuario a guardar.
     * @param archivo Ruta del archivo de destino.
     */
    public void guardarUsuario(Usuario u, String archivo) {
        try (FileWriter fw = new FileWriter(archivo, true);
             PrintWriter pw = new PrintWriter(fw)) {
            String linea = u.getNombre() + ";" + u.getContraseña() + ";" + u.getRol();
            if (u.getRol().equalsIgnoreCase("Coordinador")) {
                linea += ";" + u.getInfo();
            }
            pw.append("\n" + linea);
        } catch (IOException ex) {
            System.err.println("Error guardando usuario: " + ex.getMessage());
        }
    }

    /**
     * Sobrescribe completamente el archivo de estudiantes con la lista actual en memoria.
     * @param lista   Lista actualizada de estudiantes.
     * @param archivo Ruta del archivo a sobrescribir.
     */
    public void actualizarArchivoEstudiantes(ArrayList<Estudiante> lista, String archivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) { 
            for (Estudiante e : lista) {
                String linea = e.getRut() + ";" + e.getNombre() + ";" + e.getCarrera() + ";" +
                               e.getSemestre() + ";" + e.getCorreo() + ";" + e.getContraseña();
                pw.println(linea);
            }
        } catch (IOException ex) {
            System.err.println("Error actualizando archivo: " + ex.getMessage());
        }
    }

    /**
     * Sobrescribe completamente el archivo de usuarios con la lista actual en memoria.
     * @param lista   Lista actualizada de usuarios.
     * @param archivo Ruta del archivo a sobrescribir.
     */
    public void actualizarArchivoUsuarios(ArrayList<Usuario> lista, String archivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) {
            for (Usuario u : lista) {
                String linea = u.getNombre() + ";" + u.getContraseña() + ";" + u.getRol();
                if (u.getRol().equalsIgnoreCase("Coordinador")) {
                    linea += ";" + u.getInfo();
                }
                pw.println(linea);
            }
        } catch (IOException ex) {
            System.err.println("Error actualizando archivo: " + ex.getMessage());
        }
    }

    /**
     * Lee el archivo de notas e inyecta las calificaciones en los objetos Estudiante correspondientes.
     * @param archivo     Ruta del archivo de notas.
     * @param estudiantes Lista de estudiantes cargados en memoria.
     */
    public void cargarNotas(String archivo, ArrayList<Estudiante> estudiantes) {
        try (Scanner s = new Scanner(new File(archivo))) {
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                for (Estudiante e : estudiantes) {
                    if (e.getRut().equals(p[0])) {
                        e.agregarNota(new Nota(p[0], p[1], p[3], p[4], Double.parseDouble(p[2])));
                    }
                }
            }
        } catch (FileNotFoundException e) { 
        }
    }

    /**
     * Lee el archivo de registros de inscripción e inyecta los datos en los estudiantes.
     * @param archivo     Ruta del archivo de registros.
     * @param estudiantes Lista de estudiantes cargados en memoria.
     */
    public void cargarRegistros(String archivo, ArrayList<Estudiante> estudiantes) {
        try (Scanner s = new Scanner(new File(archivo))) {
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                for (Estudiante e : estudiantes) {
                    if (e.getRut().equals(p[0])) {
                        e.agregarRegistro(new Registro(p[0], p[1], p[2], p[3], Integer.parseInt(p[4])));
                    }
                }
            }
        } catch (FileNotFoundException e) { }
    }
    
    /**
     * Lee el archivo de relación Curso-Certificación y vincula los objetos en memoria.
     * @param archivo Ruta del archivo de cruce.
     * @param certs   Lista de certificaciones cargadas.
     * @param cursos  Lista de cursos cargados.
     */
    public void cruzarCertificaciones(String archivo, ArrayList<Certificacion> certs, ArrayList<Curso> cursos) {
        try (Scanner s = new Scanner(new File(archivo))) {
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                if (p.length < 2) continue;
                
                String idCert = p[0];
                String nrcCurso = p[1];
                
                for (Certificacion c : certs) {
                    if (c.getId().equals(idCert)) {
                        for (Curso cu : cursos) {
                            if (cu.getnRc().equals(nrcCurso)) {
                                c.agregarCurso(cu);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) { }
    }
}