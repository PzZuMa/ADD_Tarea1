package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase que genera archivos HTML a partir de una plantilla y un archivo CSV.
 */

public class GeneradorHTML {

    /**
     * Ejecuta la aplicacion.
     *
     * @param args Argumentos de la línea de comandos.
     */

    public static void main(String[] args) {
        vaciarCarpeta("salida");
        crearHTML(
                leerCSV("plantilla\\peliculas.csv"),
                leerPlantilla("plantilla\\template.html")
        );
    }

    /**
     * Lee un archivo CSV mediante un BufferedReader y va incorporando los datos de cada pelicula en el ArrayList.
     *
     * @param archivo Ruta del archivo CSV.
     * @return ArrayList de Peliculas.
     */
    public static ArrayList<Pelicula> leerCSV(String archivo) {
        var listadoPeliculas = new ArrayList<Pelicula>();

        try (BufferedReader lectorCSV = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lectorCSV.readLine()) != null) {
                var datos = linea.split(",");
                var pelicula = new Pelicula(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]), datos[3], datos[4], datos[5]);
                listadoPeliculas.add(pelicula);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listadoPeliculas;
    }

    /**
     * Lee una plantilla HTML ya creada y la devuelve en un StringBuilder para luego reemplazar los datos de cada pelicula en la plantilla.
     *
     * @param archivo: Ruta del archivo de la plantilla.
     * @return Contenido de la plantilla en un StringBuilder.
     */
    public static StringBuilder leerPlantilla(String archivo) {
        StringBuilder constructor = new StringBuilder();

        try (BufferedReader lectorPlantilla = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lectorPlantilla.readLine()) != null) {
                constructor.append(linea).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return constructor;
    }

    /**
     * Crea archivos HTML mediante un BufferedWriter y sustituyendo los datos con el metodo replace.
     *
     * @param listadoPeliculas Lista de objetos Pelicula.
     * @param constructor Contenido de la plantilla en un StringBuilder.
     */
    public static void crearHTML(ArrayList<Pelicula> listadoPeliculas, StringBuilder constructor) {
        for (Pelicula peli : listadoPeliculas) {
            try (BufferedWriter escritorHTML = new BufferedWriter(new FileWriter("salida\\" + peli.getTitulo() + " - " + peli.getId() + ".html"))) {
                escritorHTML.write(String.valueOf(constructor)
                        .replace("%titulo%", peli.getTitulo())
                        .replace("%id%", String.valueOf(peli.getId()))
                        .replace("%anho%", String.valueOf(peli.getAnho()))
                        .replace("%director%", peli.getDirector())
                        .replace("%genero%", peli.getGenero())
                        .replace("%poster%", peli.getPoster())
                );

            } catch (IOException e) {
                System.out.println("Error al crear el archivo: '" +e.getMessage()+"'");
            }
        }
        System.out.println("Todos los archivos han sido creados con exito.");
    }

    /**
     * Vacía la carpeta especificada eliminando todos los archivos en ella.
     *
     * @param carpeta El nombre o dirección de la carpeta que hay que vaciar.
     */
    public static void vaciarCarpeta(String carpeta) {
        File directorio = new File(carpeta);
        if (directorio.isDirectory()) {
            for (File archivo : Objects.requireNonNull(directorio.listFiles())) {
                if (!archivo.isDirectory()) {
                    boolean resultado = archivo.delete();
                    if (!resultado) {
                        System.out.println("No se pudo eliminar el archivo: '" + archivo.getName() + "'");
                    }
                }
            }
            System.out.println("Archivos eliminados con éxito.");
        }
    }
}