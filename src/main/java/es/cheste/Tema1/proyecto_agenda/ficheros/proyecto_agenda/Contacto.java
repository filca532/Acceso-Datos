package es.cheste.Tema1.proyecto_agenda.ficheros.proyecto_agenda;

import java.util.Objects;

public class Contacto {
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono1;
    private String telefono2;
    private String direccion;

    public Contacto() {
        super();
    }

    public Contacto(String nombre, String apellidos, String email, String telefono1, String telefono2, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return Objects.equals(nombre, contacto.nombre) && Objects.equals(apellidos, contacto.apellidos) && Objects.equals(email, contacto.email) && Objects.equals(telefono1, contacto.telefono1) && Objects.equals(telefono2, contacto.telefono2) && Objects.equals(direccion, contacto.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidos, email, telefono1, telefono2, direccion);
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", telefono1='" + telefono1 + '\'' +
                ", telefono2='" + telefono2 + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
