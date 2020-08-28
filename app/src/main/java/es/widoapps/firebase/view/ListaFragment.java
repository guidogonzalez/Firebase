package es.widoapps.firebase.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.widoapps.firebase.R;
import es.widoapps.firebase.adaptador.ListaAdaptador;
import es.widoapps.firebase.viewmodel.ListaViewModel;

public class ListaFragment extends Fragment {

    private View view;
    private ListaViewModel listaViewModel;
    private ListaAdaptador listaAdaptador = new ListaAdaptador(new ArrayList<>());
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView rvLista;
    private FloatingActionButton fabAgregar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lista, container, false);

        refreshLayout = view.findViewById(R.id.refreshLayout);
        rvLista = view.findViewById(R.id.rvLista);
        fabAgregar = view.findViewById(R.id.fabAgregar);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaViewModel = ViewModelProviders.of(this).get(ListaViewModel.class);
        listaViewModel.cargarPersonajesRemoto();

        rvLista.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLista.setAdapter(listaAdaptador);

        observarViewModel();

        fabAgregar.setOnClickListener(v -> {

            ListaFragmentDirections.ActionListaFragmentToAgregarFragment accion = ListaFragmentDirections.actionListaFragmentToAgregarFragment(null);

            Navigation.findNavController(v).navigate(accion);
        });
    }

    private void observarViewModel() {

        listaViewModel.listaPersonajes.observe(getViewLifecycleOwner(), personajes -> {

            if (personajes != null && personajes instanceof List) {

                listaAdaptador.actualizarLista(personajes);
            }
        });
    }
}