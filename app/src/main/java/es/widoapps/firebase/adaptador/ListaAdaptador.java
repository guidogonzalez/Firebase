package es.widoapps.firebase.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.widoapps.firebase.R;
import es.widoapps.firebase.databinding.ItemPersonajeBinding;
import es.widoapps.firebase.modelo.Personaje;
import es.widoapps.firebase.util.PersonajeClickListener;
import es.widoapps.firebase.view.ListaFragmentDirections;

public class ListaAdaptador extends RecyclerView.Adapter<ListaAdaptador.PersonajeViewHolder> implements PersonajeClickListener {

    private ArrayList<Personaje> listaPersonajes;

    public ListaAdaptador(ArrayList<Personaje> listaPersonajes) {

        this.listaPersonajes = listaPersonajes;
    }

    public void actualizarLista(List<Personaje> nuevaLista) {
        listaPersonajes.clear();
        listaPersonajes.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPersonajeBinding view = DataBindingUtil.inflate(inflater, R.layout.item_personaje, parent, false);

        return new PersonajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonajeViewHolder holder, int position) {

        holder.itemView.setPersonaje(listaPersonajes.get(position));
    }

    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }

    @Override
    public void onPersonajeClicked(View view) {

        String id = ((TextView) view.findViewById(R.id.tvId)).getText().toString();

        ListaFragmentDirections.ActionListaFragmentToAgregarFragment accion = ListaFragmentDirections.actionListaFragmentToAgregarFragment(null);
        accion.setId(id);
        Navigation.findNavController(view).navigate(accion);
    }

    public class PersonajeViewHolder extends RecyclerView.ViewHolder {

        public ItemPersonajeBinding itemView;

        public PersonajeViewHolder(@NonNull ItemPersonajeBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
