package es.widoapps.firebase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import es.widoapps.firebase.modelo.Personaje;
import es.widoapps.firebase.repositorio.Repositorio;

public class ListaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Personaje>> listaPersonajes = new MutableLiveData<>();

    public ListaViewModel(@NonNull Application application) {
        super(application);
    }

    public void cargarPersonajesRemoto() {

        listaPersonajes = Repositorio.getInstance().getPersonajes();
    }
}
