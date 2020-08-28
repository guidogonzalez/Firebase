package es.widoapps.firebase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import es.widoapps.firebase.modelo.Personaje;
import es.widoapps.firebase.repositorio.RepoLista;

public class ListaViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<Personaje>> listaPersonajes = new MutableLiveData<>();

    public ListaViewModel(@NonNull Application application) {
        super(application);
    }

    public void cargarPersonajesRemoto() {

        listaPersonajes = RepoLista.getInstance().getPersonajes(getApplication());
    }
}
