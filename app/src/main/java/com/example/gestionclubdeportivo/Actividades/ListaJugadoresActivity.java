package com.example.gestionclubdeportivo.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.gestionclubdeportivo.Adapters.JugadorAdapter;
import com.example.gestionclubdeportivo.Database.AppDatabase;
import com.example.gestionclubdeportivo.R;
import com.example.gestionclubdeportivo.models.Jugador;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaJugadoresActivity extends AppCompatActivity {

    private ListView listView;
    private JugadorAdapter jugadorAdapter;
    private ExecutorService executorService;
    private EditText filtroPosicion, filtroEquipo;
    private Button btnFiltrar;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jugadores);

        listView = findViewById(R.id.listViewJugadores);
        filtroPosicion = findViewById(R.id.editTextFiltroPosicion);
        filtroEquipo = findViewById(R.id.editTextFiltroEquipo);
        btnFiltrar = findViewById(R.id.buttonFiltrar);

        executorService = Executors.newSingleThreadExecutor();

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "gestionclubdeportivo")
                .fallbackToDestructiveMigration()
                .build();

        loadJugadores(null, -1);

        btnFiltrar.setOnClickListener(v -> {
            String posicion = filtroPosicion.getText().toString().trim();
            String equipoStr = filtroEquipo.getText().toString().trim();
            int equipoId = equipoStr.isEmpty() ? -1 : Integer.parseInt(equipoStr);
            loadJugadores(posicion.isEmpty() ? null : posicion, equipoId);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Jugador jugador = (Jugador) parent.getItemAtPosition(position);

            if (jugador != null) {
                Intent intent = new Intent(ListaJugadoresActivity.this, DetalleJugadorActivity.class);
                intent.putExtra("jugador_id", jugador.getId());
                intent.putExtra("jugador_nombre", jugador.getNombre());
                intent.putExtra("jugador_apellidos", jugador.getApellidos());
                intent.putExtra("jugador_sexo", jugador.getSexo());
                intent.putExtra("jugador_fecha_nacimiento", jugador.getFechaNacimiento());
                intent.putExtra("jugador_altura", jugador.getAltura());
                intent.putExtra("jugador_posicion", jugador.getPosicion());
                intent.putExtra("jugador_equipo", jugador.getEquipo());
                startActivity(intent);
            }
        });
    }

    private void loadJugadores(String posicion, int equipoId) {
        executorService.execute(() -> {
            List<Jugador> jugadores;

            if (posicion != null && equipoId != -1) {
                jugadores = db.jugadorDao().getJugadoresByPosicionAndEquipo(posicion, equipoId);
            } else if (posicion != null) {
                jugadores = db.jugadorDao().getJugadoresByPosicion(posicion);
            } else if (equipoId != -1) {
                jugadores = db.jugadorDao().getJugadoresByEquipo(equipoId);
            } else {
                jugadores = db.jugadorDao().getAllJugadores();
            }

            runOnUiThread(() -> {
                if (jugadores.isEmpty()) {
                    Toast.makeText(ListaJugadoresActivity.this, "No se encontraron jugadores", Toast.LENGTH_SHORT).show();
                }
                jugadorAdapter = new JugadorAdapter(ListaJugadoresActivity.this, jugadores);
                listView.setAdapter(jugadorAdapter);
            });
        });
    }
}
