package es.widoapps.firebase.repositorio;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import es.widoapps.firebase.modelo.Personaje;

public class Repositorio {

    public static Repositorio instancia;
    private List<Personaje> listaPersonajes = new ArrayList<>();
    private MutableLiveData<List<Personaje>> personajes = new MutableLiveData<>();
    private FirebaseFirestore bd = FirebaseFirestore.getInstance();

    public static Repositorio getInstance() {

        if (instancia == null) {
            instancia = new Repositorio();
        }

        return instancia;
    }

    public MutableLiveData<List<Personaje>> getPersonajes() {

        bd.collection("dragonball")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    if (!queryDocumentSnapshots.isEmpty()) {

                        List<DocumentSnapshot> lista = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot documentSnapshot : lista) {

                            Personaje personaje = documentSnapshot.toObject(Personaje.class);
                            personaje.setId(documentSnapshot.getId());

                            Log.d("PERSONAJE_REMOTO", personaje.nombre);

                            listaPersonajes.add(personaje);
                        }

                        personajes.setValue(listaPersonajes);
                    }

                });

        return personajes;
    }
}
