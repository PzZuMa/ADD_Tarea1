package org.example;

public class Pelicula {
    private Integer id;
    private String titulo;
    private Integer anho;
    private String director;
    private String genero;

    private String poster;

    public Pelicula(Integer id, String titulo, Integer anho, String director, String genero, String poster) {
        this.id = id;
        this.titulo = titulo;
        this.anho = anho;
        this.director = director;
        this.genero = genero;
        this.poster = poster;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAnho() {
        return anho;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    public String getPoster() {
        return poster;
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