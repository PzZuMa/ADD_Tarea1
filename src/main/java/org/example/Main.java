package org.example;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Esto lee el archivo CSV y lo incorpora a un ArrayList.

        var listadoPeliculas = new ArrayList<Pelicula>();

        try (BufferedReader lectorCSV = new BufferedReader(new FileReader("peliculas.csv"))) {
            String linea;
            while ((linea = lectorCSV.readLine()) != null) {
                var datos = linea.split(",");
                var pelicula = new Pelicula(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]), datos[3], datos[4]);
                listadoPeliculas.add(pelicula);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Esto lee la plantilla y la incorpora a un StringBuilder para luego modificarlo.

        StringBuilder constructor = new StringBuilder();

        try (BufferedReader lectorPlantilla = new BufferedReader(new FileReader("template.html"));) {
            String linea;
            while ((linea = lectorPlantilla.readLine()) != null) {
                constructor.append(linea).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Esto crea los archivos HTML reemplazando los datos de cada pelicula en la plantilla.

        for (Pelicula peli: listadoPeliculas) {
            try (BufferedWriter escritorHTML = new BufferedWriter(new FileWriter( "htmlPelis\\"+peli.getTitulo()+".html"))){

                escritorHTML.write(String.valueOf(constructor).replace("%titulo%", peli.getTitulo())
                        .replace("%anho%", String.valueOf(peli.getAnho()))
                        .replace("%director%", peli.getDirector())
                        .replace("%genero%", peli.getGenero()));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}