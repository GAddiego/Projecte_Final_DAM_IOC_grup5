/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectes;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author aleix
 */
public class Llibre implements Serializable {
    private static final long serialVersionUID = 1436768778619850352L;
    private int id;
    private String titol;
    private String autor;
    private String isbn;
    private String editorial;
    private int anyPublicacio;
    private String descripcio;
    private String sinopsi;
    private String illustrador;
    private String rutaPortada;
    private int pagines;
    private String idioma;
    private int exemplar;
    private String nota;
    private String titolOriginal;
    private String traductor;
    private byte[] portada;

    public Llibre(int id, String titol, String autor, String isbn, String editorial, int anyPublicacio, String descripcio, String sinopsi, String illustrador, String rutaPortada, int pagines, String idioma, int exemplar, String nota, String titolOriginal, String traductor) {
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anyPublicacio = anyPublicacio;
        this.descripcio = descripcio;
        this.sinopsi = sinopsi;
        this.illustrador = illustrador;
        this.rutaPortada = rutaPortada;
        this.pagines = pagines;
        this.idioma = idioma;
        this.exemplar = exemplar;
        this.nota = nota;
        this.titolOriginal = titolOriginal;
        this.traductor = traductor;
    }
    


    public Llibre() {
        
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnyPublicacio() {
        return anyPublicacio;
    }

    public void setAnyPublicacio(int anyPublicacio) {
        this.anyPublicacio = anyPublicacio;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getSinopsi() {
        return sinopsi;
    }

    public void setSinopsi(String sinopsi) {
        this.sinopsi = sinopsi;
    }

    public String getIllustrador() {
        return illustrador;
    }

    public void setIllustrador(String illustrador) {
        this.illustrador = illustrador;
    }

    public String getRutaPortada() {
        return rutaPortada;
    }

    public void setRutaPortada(String rutaPortada) {
        this.rutaPortada = rutaPortada;
    }

    public int getPagines() {
        return pagines;
    }

    public void setPagines(int pagines) {
        this.pagines = pagines;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getExemplar() {
        return exemplar;
    }

    public void setExemplar(int exemplar) {
        this.exemplar = exemplar;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTitolOriginal() {
        return titolOriginal;
    }

    public void setTitolOriginal(String titolOriginal) {
        this.titolOriginal = titolOriginal;
    }

    public String getTraductor() {
        return traductor;
    }

    public void setTraductor(String traductor) {
        this.traductor = traductor;
    }

    public byte[] getPortada() {
        return portada;
    }

    public void setPortada(byte[] b) throws IOException {
        
            this.portada = b;
    }

    @Override
    public String toString() {
        return "Llibre{" + "id=" + id + ", titol=" + titol + ", autor=" + autor + ", isbn=" + isbn + ", editorial=" + editorial + ", anyPublicacio=" + anyPublicacio + ", descripcio=" + descripcio + ", sinopsi=" + sinopsi + ", illustrador=" + illustrador + ", rutaPortada=" + rutaPortada + ", pagines=" + pagines + ", idioma=" + idioma + ", exemplar=" + exemplar + ", nota=" + nota + ", titolOriginal=" + titolOriginal + ", traductor=" + traductor + '}';
    }
    
    

}
