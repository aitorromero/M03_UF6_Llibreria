package model;

import java.io.*;

public class Llibre implements Serializable {

    private String isbn;
    private String titol;
    private String autor;
    private String editorial;
    private int anyEdicio;
    private int estoc;

    public Llibre() {
    }

    public Llibre(String isbn, String titol, String autor, String editorial, int anyEdicio, int estoc) {
        this.isbn = isbn;
        this.titol = titol;
        this.autor = autor;
        this.editorial = editorial;
        this.anyEdicio = anyEdicio;
        this.estoc = estoc;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnyEdicio() {
        return anyEdicio;
    }

    public void setAnyEdicio(int anyEdicio) {
        this.anyEdicio = anyEdicio;
    }

    public int getEstoc() {
        return estoc;
    }

    public void setEstoc(int estoc) {
        this.estoc = estoc;
    }

    @Override
    public String toString() {
        return "Llibre{" + "isbn=" + isbn + ", titol=" + titol + ", autor=" + autor + ", editorial=" + editorial + ", anyEdicio=" + anyEdicio + ", estoc=" + estoc + '}';
    }

    

}
