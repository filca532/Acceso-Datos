package es.cheste.Tema1.ejercicios_entregables.Ejer4;



public class Modulo implements Comparable<Modulo> {
    private String nombre;
    private int horas;
    private double nota;

    public Modulo() {
        super();
    }

    public Modulo(String nombre, int horas, double nota) {
        this.nombre = nombre;
        this.horas = horas;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHoras() {
        return horas;
    }

    public double getNota() {
        return nota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "nombre='" + nombre + '\'' +
                ", horas=" + horas +
                ", nota=" + nota +
                '}';
    }

    @Override
    public int compareTo(Modulo o) {
        return this.nombre.compareTo(o.nombre);
    }
}
