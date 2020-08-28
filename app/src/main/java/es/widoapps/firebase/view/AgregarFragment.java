package es.widoapps.firebase.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import es.widoapps.firebase.R;
import es.widoapps.firebase.modelo.Personaje;
import es.widoapps.firebase.viewmodel.AgregarViewModel;

public class AgregarFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText etNombre;
    private EditText etUrlImagen;
    private Button bAceptar;

    private Personaje personaje;
    private String idPersonaje;

    private AgregarViewModel agregarViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_agregar, container, false);

        etNombre = view.findViewById(R.id.etNombre);
        etUrlImagen = view.findViewById(R.id.etUrlImagen);
        bAceptar = view.findViewById(R.id.bAceptar);
        bAceptar.setOnClickListener(this);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {

            personaje = (Personaje) getArguments().getSerializable("Personaje");
            idPersonaje = personaje.getId();

            etNombre.setText(personaje.nombre);
            etUrlImagen.setText(personaje.urlImagen);
        }

        agregarViewModel = ViewModelProviders.of(this).get(AgregarViewModel.class);
    }

    public void editarPersonaje() {

        String nombre = etNombre.getText().toString();
        String urlImagen = etUrlImagen.getText().toString();

        Personaje personaje = new Personaje(nombre, urlImagen);
        personaje.setId(idPersonaje);

        agregarViewModel.editarPersonaje(getContext(), personaje, idPersonaje);
    }

    public void agregarPersonaje() {

        String nombre = etNombre.getText().toString();
        String urlImagen = etUrlImagen.getText().toString();

        if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(urlImagen)) {

            agregarViewModel.agregarPersonaje(getContext(), nombre, urlImagen);

        } else {

            Toast.makeText(getContext(), "Debes de rellenar los campos.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        if (getArguments() != null) {

            editarPersonaje();

        } else {

            agregarPersonaje();
        }

        Navigation.findNavController(v).navigate(R.id.action_AgregarFragment_to_ListaFragment);
    }
}