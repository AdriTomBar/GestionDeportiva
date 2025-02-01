package com.example.gestionclubdeportivo.Actividades;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jugadores);

        listView = findViewById(R.id.listViewJugadores);

        executorService = Executors.newSingleThreadExecutor();

        loadJugadores();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Obtén el jugador seleccionado
            Jugador jugador = (Jugador) parent.getItemAtPosition(position);

            if (jugador != null) {
                // Crear una nueva Intent para la actividad de detalles del jugador
                Intent intent = new Intent(ListaJugadoresActivity.this, DetalleJugadorActivity.class);
                // Pasar toda la información del jugador
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

    private void loadJugadores() {
        executorService.execute(() -> {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "gestionclubdeportivo")
                    .addMigrations(AppDatabase.MIGRATION_2_3) // Apply the migration
                    .fallbackToDestructiveMigration() // Optional: fallback to destructive migration
                    .build();

            List<Jugador> jugadores = db.jugadorDao().getAllJugadores();

            runOnUiThread(() -> {
                if (jugadores.isEmpty()) {
                    Toast.makeText(ListaJugadoresActivity.this, "No hay jugadores registrados", Toast.LENGTH_SHORT).show();
                }

                jugadorAdapter = new JugadorAdapter(ListaJugadoresActivity.this, jugadores);
                listView.setAdapter(jugadorAdapter);
            });
        });
    }
}
