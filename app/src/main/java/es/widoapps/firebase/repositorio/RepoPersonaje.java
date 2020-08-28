package es.widoapps.firebase.repositorio;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import es.widoapps.firebase.modelo.Personaje;

public class RepoPersonaje {

    public static RepoPersonaje instancia;
    private FirebaseFirestore bd = FirebaseFirestore.getInstance();

    public static RepoPersonaje getInstance() {

        if (instancia == null) {
            instancia = new RepoPersonaje();
        }

        return instancia;
    }

    public void editarPersonaje(Context context, Personaje personaje, String id) {

        bd.collection("dragonball")
                .document(id)
                .set(personaje, SetOptions.merge())
                .addOnSuccessListener(documentReference -> {

                    Toast.makeText(context, "Actualizado correctamente.", Toast.LENGTH_SHORT).show();

                })
                .addOnFailureListener(e -> {

                    Toast.makeText(context, "No se ha podido actualizar.", Toast.LENGTH_SHORT).show();
                });
    }

    public void agregarPersonaje(Context context, String nombre, String urlImagen) {

        DocumentReference ref = bd.collection("dragonball").document();
        String id = ref.getId();

        Map<String, Object> datos = new HashMap<>();
        datos.put("id", id);
        datos.put("nombre", nombre);
        datos.put("urlImagen", urlImagen);

        ref.set(datos)
                .addOnSuccessListener(aVoid -> {

                    Toast.makeText(context, "Agregar correctamente.", Toast.LENGTH_SHORT).show();

                })
                .addOnFailureListener(e -> {

                    Toast.makeText(context, "No se ha podido añadir.", Toast.LENGTH_SHORT).show();
                });
    }

    public void borrarPersonaje(Context context, String id) {

        bd.collection("dragonball")
                .document(id)
                .delete()
                .addOnSuccessListener(aVoid -> {

                    Toast.makeText(context, "Borrado con éxito.", Toast.LENGTH_SHORT).show();

                })
                .addOnFailureListener(e -> {

                    Toast.makeText(context, "No se ha podido borrar.", Toast.LENGTH_SHORT).show();
                });
    }
}