package com.example.gestionclubdeportivo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gestionclubdeportivo.R;
import com.example.gestionclubdeportivo.models.Jugador;

import java.util.List;

public class JugadorAdapter extends ArrayAdapter<Jugador> {

    private Context context;
    private List<Jugador> jugadores;

    public JugadorAdapter(Context context, List<Jugador> jugadores) {
        super(context, 0, jugadores);
        this.context = context;
        this.jugadores = jugadores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_jugador, parent, false);
        }

        // Encuentra los TextViews en el layout del item
        TextView nombreTextView = convertView.findViewById(R.id.nombreTextView);
        TextView apellidosTextView = convertView.findViewById(R.id.apellidosTextView);
        TextView equipoTextView = convertView.findViewById(R.id.equipoTextView);

        // Obtén el objeto Jugador correspondiente a la posición
        Jugador jugador = getItem(position);
        int equipo = jugador.getEquipo();
        // Verifica que el jugador no sea null antes de establecer el texto
        if (jugador != null) {
            // Establece los valores en los TextViews
            nombreTextView.setText(jugador.getNombre() != null ? jugador.getNombre() : "Nombre no disponible");
            apellidosTextView.setText(jugador.getApellidos() != null ? jugador.getApellidos() : "Apellidos no disponibles");
            equipoTextView.setText(equipo != -1 ? String.valueOf(equipo) : "Equipo no disponible");

        }

        return convertView;
    }


}
