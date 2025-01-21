package com.example.gestionclubdeportivo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionclubdeportivo.models.Equipo;

import java.util.ArrayList;
import java.util.List;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder> {

    private List<Equipo> equiposOriginal; // Lista completa de datos
    private List<Equipo> equiposFiltrados; // Lista filtrada

    public EquipoAdapter(List<Equipo> equipos) {
        this.equiposOriginal = equipos;
        this.equiposFiltrados = new ArrayList<>(equipos); // Inicialmente contiene todos los datos
    }

    @NonNull
    @Override
    public EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipo, parent, false);
        return new EquipoViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return equiposFiltrados.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoViewHolder holder, int position) {
        Equipo equipo = equiposFiltrados.get(position);

        holder.imgEquipo.setImageResource(equipo.getImagen());
        holder.tvNombreEquipo.setText(equipo.getNombre());
        holder.tvCategoria.setText(equipo.getCategoria());
        holder.tvFederado.setText(equipo.isFederado() ? "Federado: Sí" : "Federado: No");

        // Configurar el click listener
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), InfoEquipo.class);
            intent.putExtra("imagen", equipo.getImagen());
            intent.putExtra("nombre", equipo.getNombre());
            intent.putExtra("patrocinador", equipo.getPatrocinador());
            intent.putExtra("categoria", equipo.getCategoria());
            intent.putExtra("modalidad", equipo.getModalidad());
            intent.putExtra("federado", equipo.isFederado());
            intent.putExtra("diaPartido", equipo.getDiaPartido());
            intent.putExtra("horaPartido", equipo.getHoraPartido());
            intent.putExtra("entrenamientos", equipo.getEntrenamientos());
            intent.putExtra("contacto", equipo.getContacto());
            intent.putExtra("telefono", equipo.getTelefono());
            view.getContext().startActivity(intent);
        });
    }

    public void filtrar(String categoria, String modalidad) {
        equiposFiltrados.clear();

        // Si se selecciona "Todos", considera el filtro como vacío
        boolean filtrarCategoria = !categoria.equalsIgnoreCase("Todos");
        boolean filtrarModalidad = !modalidad.equalsIgnoreCase("Todos");

        if (!filtrarCategoria && !filtrarModalidad) {
            equiposFiltrados.addAll(equiposOriginal); // Si no hay filtros, muestra todos
        } else {
            for (Equipo equipo : equiposOriginal) {
                boolean coincideCategoria = !filtrarCategoria || equipo.getCategoria().equalsIgnoreCase(categoria);
                boolean coincideModalidad = !filtrarModalidad || equipo.getModalidad().equalsIgnoreCase(modalidad);

                if (coincideCategoria && coincideModalidad) {
                    equiposFiltrados.add(equipo);
                }
            }
        }

        notifyDataSetChanged(); // Actualiza la vista
    }


    static class EquipoViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEquipo;
        TextView tvNombreEquipo, tvCategoria, tvFederado;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEquipo = itemView.findViewById(R.id.imgEquipo);
            tvNombreEquipo = itemView.findViewById(R.id.tvNombreEquipo);
            tvCategoria = itemView.findViewById(R.id.tvCategoria);
            tvFederado = itemView.findViewById(R.id.tvFederado);
        }
    }
}
