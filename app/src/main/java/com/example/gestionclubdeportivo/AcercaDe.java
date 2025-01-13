package com.example.gestionclubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AcercaDe extends AppCompatActivity {

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
        String nombreRecogido = i.getStringExtra("nombre");
        String edadRecogida = i.getStringExtra("edad");
        String saludo = "Bienvenido "+nombreRecogido+" a la aplicación de Club Deportivo IES CIERVA";
        Toast t = Toast.makeText(this, saludo, Toast.LENGTH_LONG);
        t.show();
    }
}