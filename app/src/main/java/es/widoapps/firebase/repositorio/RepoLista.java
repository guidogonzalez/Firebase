package es.widoapps.firebase.repositorio;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import es.widoapps.firebase.modelo.Personaje;

public class RepoLista {

    public static RepoLista instancia;
    private ArrayList<Personaje> listaPersonajes = new ArrayList<>();
    private MutableLiveData<ArrayList<Personaje>> personajes = new MutableLiveData<>();
    private FirebaseFirestore bd = FirebaseFirestore.getInstance();

    public static RepoLista getInstance() {

        if (instancia == null) {
            instancia = new RepoLista();
        }

        return instancia;
    }

    public MutableLiveData<ArrayList<Personaje>> getPersonajes(Context context) {

        listaPersonajes.clear();

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

                        Toast.makeText(context, "Lista cargada correctamente.", Toast.LENGTH_SHORT).show();
                    }

                })
                .addOnFailureListener(e -> {

                    Toast.makeText(context, "No se ha podido cargar.", Toast.LENGTH_SHORT).show();
                });

        return personajes;
    }
}
