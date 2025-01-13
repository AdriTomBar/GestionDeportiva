package com.example.gestionclubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.intellij.lang.annotations.Identifier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        Button btSalir = findViewById(R.id.btSalir);
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });
        Button btJugadores = findViewById(R.id.btJugadores);

        btJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirJugadores();
            }
        });

        Button btAcercaDe = findViewById(R.id.btAcercaDe);
        btAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarEdad();
            }
        });
    }



    public void comprobarEdad() {
        TextView edad = findViewById(R.id.inputEdadPpal);
        String textoEdad = edad.getText().toString().trim();

        if (textoEdad.isEmpty()) {
            Toast.makeText(this, "Por favor introduce una edad válida", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int edadComprobar = Integer.parseInt(textoEdad);
            if (edadComprobar < 16) {
                Toast.makeText(this, "No puedes iniciar sesión si tienes menos de 16 años", Toast.LENGTH_LONG).show();
            } else {
                abrirAcercaDe();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor introduce una edad válida", Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirAcercaDe(){
        Intent i = new Intent(this, AcercaDe.class);
        TextView edad = findViewById(R.id.inputEdadPpal);
        String textoEdad = edad.getText().toString().trim();

        TextView nombre = findViewById(R.id.inputNombrePpal);
        String textoNombre = nombre.getText().toString().trim();

        i.putExtra("edad",textoEdad);
        i.putExtra("nombre",textoNombre);

        startActivity(i);
    }

    public void abrirJugadores(){
        Intent i = new Intent(this, Jugadores.class);
        startActivity(i);
    }

    public void salir (){
        finish();
    }
}