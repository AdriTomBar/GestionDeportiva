package com.example.gestionclubdeportivo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import com.example.gestionclubdeportivo.models.Equipo;

import java.util.ArrayList;
import java.util.List;

public class Equipos extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerViewEquipos;
    private EquipoAdapter equipoAdapter;
    private Spinner spinnerCategoria, spinnerModalidad;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);

        recyclerViewEquipos = findViewById(R.id.recyclerViewEquipos);
        recyclerViewEquipos.setLayoutManager(new LinearLayoutManager(this));

        equipoAdapter = new EquipoAdapter(getEquipos());
        recyclerViewEquipos.setAdapter(equipoAdapter);

        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        spinnerModalidad = findViewById(R.id.spinnerModalidad);

        // Listener para detectar cambios en los filtros
        AdapterView.OnItemSelectedListener filtroListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categoria = spinnerCategoria.getSelectedItem().toString();
                String modalidad = spinnerModalidad.getSelectedItem().toString();

                equipoAdapter.filtrar(categoria, modalidad);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerCategoria.setOnItemSelectedListener(filtroListener);
        spinnerModalidad.setOnItemSelectedListener(filtroListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Mostrar el menú definido en menu.xml
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Acciones a realizar según la opción seleccionada
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.jugadores) {
            // Iniciar  actividad  Jugadores
            Intent intentJugadores = new Intent(this, Jugadores.class);
            startActivity(intentJugadores);
            return true;
        }
        else if (itemId == R.id.equipos) {
            // Iniciar  actividad  Equipos
            Intent intentEquipos = new Intent(this, Equipos.class);
            startActivity(intentEquipos);
            return true;
        }
        else if (itemId == R.id.informes) {
            // Iniciar  actividad  Informes
            Intent intentInformes = new Intent(this, Informes.class);
            startActivity(intentInformes);
            return true;
        }
        else if (itemId == R.id.acercaDe) {
            // Iniciar actividad AcercaDe
            Intent intentAcercaDe = new Intent(this, AcercaDe.class);
            startActivity(intentAcercaDe);
            return true;
        }
        else if (itemId == R.id.salir) {
            Intent i = new Intent(this , MainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private List<Equipo> getEquipos() {
        List<Equipo> equipos = new ArrayList<>();

        equipos.add(new Equipo(
                "Tigres F.C.",                      // nombre
                "Adidas",                           // patrocinador
                "Junior",                           // categoria
                "Masculino",                        // modalidad
                true,                               // federado
                R.drawable.ic_launcher_foreground,  // imagen (reemplazar con una imagen válida)
                "Sábado",                           // diaPartido
                "18:00",                            // horaPartido
                "Lunes y Miércoles a las 18:00",     // entrenamientos
                "Juan Pérez",                       // contacto
                "123456789"                          // telefono
        ));

        equipos.add(new Equipo(
                "Panteras",                         // nombre
                "Nike",                             // patrocinador
                "Infantil",                         // categoria
                "Femenino",                         // modalidad
                false,                              // federado
                R.drawable.ic_launcher_foreground,  // imagen (reemplazar con una imagen válida)
                "Domingo",                          // diaPartido
                "16:00",                            // horaPartido
                "Martes y Jueves a las 17:00",       // entrenamientos
                "Ana Gómez",                        // contacto
                "987654321"                         // telefono
        ));

        equipos.add(new Equipo(
                "Águilas",                          // nombre
                "Puma",                             // patrocinador
                "Cadete",                           // categoria
                "Masculino",                        // modalidad
                true,                               // federado
                R.drawable.ic_launcher_foreground,  // imagen (reemplazar con una imagen válida)
                "Sábado",                           // diaPartido
                "20:00",                            // horaPartido
                "Miércoles y Viernes a las 18:00",   // entrenamientos
                "Carlos López",                     // contacto
                "1122334455"                        // telefono
        ));

        return equipos;
    }

}
