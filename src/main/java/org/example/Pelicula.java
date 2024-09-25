package org.example;

import lombok.Data;

/**
 * Clase que representa una película y define sus atributos.
 */
@Data
public class Pelicula {
    private Integer id;
    private String titulo;
    private Integer anho;
    private String director;
    private String genero;
    private String poster;


    /**
     * Constructor de la clase Pelicula.
     *
     * @param id       Identificador de la película.
     * @param titulo   Título de la película.
     * @param anho     Año de lanzamiento de la película.
     * @param director Director de la película.
     * @param genero   Género de la película.
     * @param poster   URL del póster de la película.
     */
    public Pelicula(Integer id, String titulo, Integer anho, String director, String genero, String poster) {
        this.id = id;
        this.titulo = titulo;
        this.anho = anho;
        this.director = director;
        this.genero = genero;
        this.poster = poster;
    }
    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anho=" + anho +
                ", director='" + director + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}