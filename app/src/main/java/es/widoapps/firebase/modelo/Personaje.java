package es.widoapps.firebase.modelo;

public class Personaje {

    public String id;
    public String nombre;
    public String urlImagen;

    public Personaje() {

    }

    public Personaje(String nombre, String urlImagen) {
        setId(id);
        this.nombre = nombre;
        this.urlImagen = urlImagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}