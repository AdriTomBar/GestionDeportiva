package com.example.gestionclubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AcercaDe extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_acerca_de);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String saludo = "Bienvenido  a la aplicación de Club Deportivo IES CIERVA";
        Toast t = Toast.makeText(this, saludo, Toast.LENGTH_LONG);
        t.show();
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
            // Cerrar
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}