package es.cheste.Tema1.ejercicios_entregables.Ejer8;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonajeWrapper {
    @SerializedName("personatges")
    private List<Personaje> personajes;

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
}
