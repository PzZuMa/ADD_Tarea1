package org.example;

import java.io.*;
import java.util.ArrayList;

public class Main {
//    Este metodo lee el archivo CSV y lo devuelve en un ArrayList.
    public static ArrayList<Pelicula> leerCSV(String archivo) {
        var listadoPeliculas = new ArrayList<Pelicula>();

        try (BufferedReader lectorCSV = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lectorCSV.readLine()) != null) {
                var datos = linea.split(",");
                var pelicula = new Pelicula(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]), datos[3], datos[4]);
                listadoPeliculas.add(pelicula);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listadoPeliculas;
    }

//    Este metodo lee la plantilla y la devuelve en un StringBuilder para luego modificarla con el metodo replace.

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

//    Este metodo crea los archivos HTML reemplazando los datos de cada pelicula en la plantilla.

    public static void crearHTML(ArrayList<Pelicula> listadoPeliculas, StringBuilder constructor) {
        for (Pelicula peli: listadoPeliculas) {
            try (BufferedWriter escritorHTML = new BufferedWriter(new FileWriter( "htmlPelis\\"+peli.getTitulo()+".html"))){

                escritorHTML.write(String.valueOf(constructor)
                        .replace("%titulo%", peli.getTitulo())
                        .replace("%id%", String.valueOf(peli.getId()))
                        .replace("%anho%", String.valueOf(peli.getAnho()))
                        .replace("%director%", peli.getDirector())
                        .replace("%genero%", peli.getGenero()));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        crearHTML(leerCSV("peliculas.csv"), leerPlantilla("template.html"));
    }
}