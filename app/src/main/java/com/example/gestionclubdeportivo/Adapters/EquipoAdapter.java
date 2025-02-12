package com.example.gestionclubdeportivo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionclubdeportivo.Actividades.InfoEquipo;
import com.example.gestionclubdeportivo.R;
import com.example.gestionclubdeportivo.models.Equipo;

import java.util.ArrayList;
import java.util.List;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder> {
    private List<Equipo> equipos;
    private List<Equipo> equiposFiltrados;
    private Context context;

    public EquipoAdapter(Context context, List<Equipo> equipos) {
        this.context = context;
        this.equipos = equipos;
        this.equiposFiltrados = new ArrayList<>(equipos);
    }

    @Override
    public EquipoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipo, parent, false);
        return new EquipoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EquipoViewHolder holder, int position) {
        Equipo equipo = equiposFiltrados.get(position);
        holder.tvNombre.setText(equipo.getNombre());
        holder.tvFederado.setText(equipo.isFederado() ? "Federado: Sí" : "Federado: No");
        holder.tvCategoria.setText(equipo.getCategoria());
        holder.tvModalidad.setText(equipo.getModalidad());
        Bitmap bitmap = BitmapFactory.decodeByteArray(equipo.getImagen(), 0, equipo.getImagen().length);
        holder.imgEquipo.setImageBitmap(bitmap);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, InfoEquipo.class);
            intent.putExtra("imagen", equipo.getImagen());
            intent.putExtra("nombre", equipo.getNombre());
            intent.putExtra("patrocinador", equipo.getPatrocinador());
            intent.putExtra("categoria", equipo.getCategoria());
            intent.putExtra("modalidad", equipo.getModalidad());
            intent.putExtra("federado", equipo.isFederado());
            intent.putExtra("dia_partido", equipo.getDiaPartido());
            intent.putExtra("hora_partido", equipo.getHoraPartido());
            intent.putExtra("entrenamientos", equipo.getEntrenamientos());
            intent.putExtra("persona_contacto", equipo.getPersonaContacto());
            intent.putExtra("numero_contacto", equipo.getNumeroContacto());
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return equiposFiltrados.size();
    }

    public void filtrar(String categoria, String modalidad) {
        equiposFiltrados.clear();
        if (categoria.equals("Todos") && modalidad.equals("Todos")) {
            equiposFiltrados.addAll(equipos);
        } else {
            for (Equipo equipo : equipos) {
                if ((categoria.equals("Todos") || equipo.getCategoria().equals(categoria)) &&
                        (modalidad.equals("Todos") || equipo.getModalidad().equals(modalidad))) {
                    equiposFiltrados.add(equipo);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class EquipoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvFederado, tvCategoria, tvModalidad;
        ImageView imgEquipo;

        public EquipoViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvFederado = itemView.findViewById(R.id.tvFederado);
            tvCategoria = itemView.findViewById(R.id.tvCategoria);
            tvModalidad = itemView.findViewById(R.id.tvModalidad);
            imgEquipo = itemView.findViewById(R.id.imgEquipo);
        }
    }
}