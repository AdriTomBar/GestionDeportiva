// app/src/main/java/com/example/gestionclubdeportivo/Actividades/ListaJugadoresActivity.java
package com.example.gestionclubdeportivo.Actividades;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.gestionclubdeportivo.Adapters.JugadorAdapter;
import com.example.gestionclubdeportivo.Database.AppDatabase;
import com.example.gestionclubdeportivo.R;
import com.example.gestionclubdeportivo.models.Equipo;
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
    }

    private void loadJugadores() {
        executorService.execute(() -> {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "gestionclubdeportivo")
                    .addMigrations(AppDatabase.MIGRATION_3_4) // Apply the migration
                    .fallbackToDestructiveMigration() // Optional: fallback to destructive migration
                    .build();

            List<Jugador> jugadores = db.jugadorDao().getAllJugadores();

            for (Jugador jugador : jugadores) {
                Equipo equipo = db.equipoDao().getEquipoById(jugador.getEquipo());
                if (equipo != null) {
                    jugador.setEquipoNombre(equipo.getNombre());
                }
            }

            runOnUiThread(() -> {
                if (jugadores.isEmpty()) {
                    Toast.makeText(ListaJugadoresActivity.this, "No hay jugadores registrados", Toast.LENGTH_SHORT).show();
                }

                jugadorAdapter = new JugadorAdapter(ListaJugadoresActivity.this, jugadores);
                listView.setAdapter(jugadorAdapter);
            });
        });
    }

    private void insertJugadoresDePrueba(AppDatabase db) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            // Crear jugadores de prueba
            Jugador jugador1 = new Jugador();
            jugador1.setNombre("Juan");
            jugador1.setApellidos("Pérez");
            jugador1.setSexo("Masculino");
            jugador1.setFechaNacimiento("1995-07-14");
            jugador1.setAltura(1.75f);
            jugador1.setPosicion("Delantero");
            jugador1.setEquipo(1); // Asumimos que el equipo con ID 1 ya existe

            Jugador jugador2 = new Jugador();
            jugador2.setNombre("Ana");
            jugador2.setApellidos("Gómez");
            jugador2.setSexo("Femenino");
            jugador2.setFechaNacimiento("1997-03-22");
            jugador2.setAltura(1.65f);
            jugador2.setPosicion("Centrocampista");
            jugador2.setEquipo(2); // Asumimos que el equipo con ID 2 ya existe

            // Insertar los jugadores de prueba en la base de datos
            db.jugadorDao().insert(jugador1);
            db.jugadorDao().insert(jugador2);

            // Ejecutar el Toast en el hilo principal
            runOnUiThread(() -> Toast.makeText(this, "Jugadores de prueba insertados", Toast.LENGTH_SHORT).show());
        });
    }
}