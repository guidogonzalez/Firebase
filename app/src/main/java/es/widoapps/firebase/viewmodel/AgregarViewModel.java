package es.widoapps.firebase.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import es.widoapps.firebase.modelo.Personaje;
import es.widoapps.firebase.repositorio.RepoPersonaje;

public class AgregarViewModel extends AndroidViewModel {

    public AgregarViewModel(@NonNull Application application) {
        super(application);
    }

    public void editarPersonaje(Context context, Personaje personaje, String id) {

        RepoPersonaje.getInstance().editarPersonaje(context, personaje, id);
    }

    public void agregarPersonaje(Context context, String nombre, String urlImagen) {

        RepoPersonaje.getInstance().agregarPersonaje(context, nombre, urlImagen);
    }

    public void borrarPersonaje(Context context, String id) {

        RepoPersonaje.getInstance().borrarPersonaje(context, id);
    }
}
