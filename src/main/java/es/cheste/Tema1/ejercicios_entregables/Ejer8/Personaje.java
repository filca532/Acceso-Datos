package es.cheste.Tema1.ejercicios_entregables.Ejer8;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Objects;

public class Personaje implements Comparable<Personaje> {
    @SerializedName("name")
    private String nombre;
    @SerializedName("height")
    private String altura;
    @SerializedName("mass")
    private String peso;
    @SerializedName("hair_color")
    private String colorPelo;
    @SerializedName("skin_color")
    private String colorPiel;
    @SerializedName("eye_color")
    private String colorOjos;
    @SerializedName("birth_year")
    private String fechaNacimiento;
    @SerializedName("gender")
    private String genero;
    @SerializedName("homeworld")
    private String planetaNatal;
    @SerializedName("films")
    private ArrayList<String> peliculas;
    @SerializedName("species")
    private ArrayList<String> especies;
    @SerializedName("vehicles")
    private ArrayList<String> vehiculos;
    @SerializedName("starships")
    private ArrayList<String> naves;
    @SerializedName("created")
    private String creado;
    @SerializedName("edited")
    private String editado;
    @SerializedName("url")
    private String url;

    public Personaje() {
        super();
    }

    public Personaje(String nombre, String altura, String peso, String colorPelo, String colorPiel, String colorOjos, String fechaNacimiento, String genero, String mundoNatal, ArrayList<String> peliculas, ArrayList<String> especies, ArrayList<String> vehiculos, ArrayList<String> naves, String creado, String editado, String url) {
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.colorPelo = colorPelo;
        this.colorPiel = colorPiel;
        this.colorOjos = colorOjos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.planetaNatal = mundoNatal;
        this.peliculas = peliculas;
        this.especies = especies;
        this.vehiculos = vehiculos;
        this.naves = naves;
        this.creado = creado;
        this.editado = editado;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getColorPelo() {
        return colorPelo;
    }

    public void setColorPelo(String colorPelo) {
        this.colorPelo = colorPelo;
    }

    public String getColorPiel() {
        return colorPiel;
    }

    public void setColorPiel(String colorPiel) {
        this.colorPiel = colorPiel;
    }

    public String getColorOjos() {
        return colorOjos;
    }

    public void setColorOjos(String colorOjos) {
        this.colorOjos = colorOjos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlanetaNatal() {
        return planetaNatal;
    }

    public void setPlanetaNatal(String planetaNatal) {
        this.planetaNatal = planetaNatal;
    }

    public ArrayList<String> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<String> peliculas) {
        this.peliculas = peliculas;
    }

    public ArrayList<String> getEspecies() {
        return especies;
    }

    public void setEspecies(ArrayList<String> especies) {
        this.especies = especies;
    }

    public ArrayList<String> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<String> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public ArrayList<String> getNaves() {
        return naves;
    }

    public void setNaves(ArrayList<String> naves) {
        this.naves = naves;
    }

    public String getCreado() {
        return creado;
    }

    public void setCreado(String creado) {
        this.creado = creado;
    }

    public String getEditado() {
        return editado;
    }

    public void setEditado(String editado) {
        this.editado = editado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personaje that = (Personaje) o;
        return altura == that.altura && peso == that.peso && Objects.equals(nombre, that.nombre) && Objects.equals(colorPelo, that.colorPelo) && Objects.equals(colorPiel, that.colorPiel) && Objects.equals(colorOjos, that.colorOjos) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(genero, that.genero) && Objects.equals(planetaNatal, that.planetaNatal) && Objects.equals(peliculas, that.peliculas) && Objects.equals(especies, that.especies) && Objects.equals(vehiculos, that.vehiculos) && Objects.equals(naves, that.naves) && Objects.equals(creado, that.creado) && Objects.equals(editado, that.editado) && Objects.equals(url, that.url);
    }

    @Override
    public int compareTo(Personaje o) {
        if (this.peliculas.size() > o.peliculas.size()) {
            return -1;
        } else if (this.peliculas.size() < o.peliculas.size()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, altura, peso, colorPelo, colorPiel, colorOjos, fechaNacimiento, genero, planetaNatal, peliculas, especies, vehiculos, naves, creado, editado, url);
    }

    @Override
    public String toString() {
        return "Personajes{" +
                "nombre='" + nombre + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", colorPelo='" + colorPelo + '\'' +
                ", colorPiel='" + colorPiel + '\'' +
                ", colorOjos='" + colorOjos + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", genero='" + genero + '\'' +
                ", planetaNatal='" + planetaNatal + '\'' +
                ", peliculas=" + peliculas +
                ", especies=" + especies +
                ", vehiculos=" + vehiculos +
                ", naves=" + naves +
                ", creado='" + creado + '\'' +
                ", editado='" + editado + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
