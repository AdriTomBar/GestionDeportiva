package com.example.gestionclubdeportivo.Actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.gestionclubdeportivo.Adapters.EquipoAdapter;
import com.example.gestionclubdeportivo.R;
import com.example.gestionclubdeportivo.Database.AppDatabase;
import com.example.gestionclubdeportivo.models.Equipo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Equipos extends AppCompatActivity {

    private Spinner spinnerCategoria, spinnerModalidad;
    private EquipoAdapter equipoAdapter; // Keep this as an instance variable
    private AppDatabase db;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);

        // Initialize the database
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "gestionclubdeportivo")
                .fallbackToDestructiveMigration()
                .build();

        // Initialize ExecutorService
        executorService = Executors.newSingleThreadExecutor();

        // Initialize the Spinners
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        spinnerModalidad = findViewById(R.id.spinnerModalidad);

        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this,
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        ArrayAdapter<CharSequence> adapterModalidad = ArrayAdapter.createFromResource(this,
                R.array.modalidades, android.R.layout.simple_spinner_item);
        adapterModalidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModalidad.setAdapter(adapterModalidad);

        // Initialize the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEquipos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load data in background and insert a test team if database is empty
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Equipo> equipos = db.equipoDao().getAllEquipos();

                // If the list is empty, insert a default team
                if (equipos.isEmpty()) {
                    Equipo equipo1 = new Equipo("Equipo 1", "Adidas", "Juvenil", "Masculino", true,
                            R.drawable.murcia_basket, "Murcia Basket Contacto", "644756345", "Lunes 13", "13:00", "Lunes y Viernes");
                    db.equipoDao().insertAll(equipo1);

                    // Reload the list after inserting the test team
                    equipos = db.equipoDao().getAllEquipos();
                }

                // Update the UI with the data in the main thread
                List<Equipo> finalEquipos = equipos;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Only update the adapter when data is ready
                        equipoAdapter = new EquipoAdapter(Equipos.this, finalEquipos);
                        recyclerView.setAdapter(equipoAdapter);
                    }
                });
            }
        });

        // Filter teams when a category or modality is selected
        AdapterView.OnItemSelectedListener filtroListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Make sure equipoAdapter is initialized before calling filtrar
                if (equipoAdapter != null) {
                    String categoria = spinnerCategoria.getSelectedItem().toString();
                    String modalidad = spinnerModalidad.getSelectedItem().toString();
                    equipoAdapter.filtrar(categoria, modalidad);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        spinnerCategoria.setOnItemSelectedListener(filtroListener);
        spinnerModalidad.setOnItemSelectedListener(filtroListener);
    }
}
