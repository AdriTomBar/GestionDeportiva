package com.example.gestionclubdeportivo.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionclubdeportivo.R;

public class DetalleJugadorActivity extends AppCompatActivity {

    private TextView nombreTextView;
    private TextView apellidosTextView;
    private TextView sexoTextView;
    private TextView fechaNacimientoTextView;
    private TextView alturaTextView;
    private TextView posicionTextView;
    private TextView equipoTextView;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_jugador);

        // Inicializar los TextViews
        nombreTextView = findViewById(R.id.nombreTextViewDetalle);
        apellidosTextView = findViewById(R.id.apellidosTextViewDetalle);
        sexoTextView = findViewById(R.id.sexoTextViewDetalle);
        fechaNacimientoTextView = findViewById(R.id.fechaNacimientoTextViewDetalle);
        alturaTextView = findViewById(R.id.alturaTextViewDetalle);
        posicionTextView = findViewById(R.id.posicionTextViewDetalle);
        equipoTextView = findViewById(R.id.equipoTextViewDetalle);
        btnSalir = findViewById(R.id.btnSalir);

        // Obtener los datos pasados desde ListaJugadoresActivity
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("jugador_nombre");
        String apellidos = intent.getStringExtra("jugador_apellidos");
        String sexo = intent.getStringExtra("jugador_sexo");
        String fechaNacimiento = intent.getStringExtra("jugador_fecha_nacimiento");
        float altura = intent.getFloatExtra("jugador_altura", 0);
        String posicion = intent.getStringExtra("jugador_posicion");
        int equipo = intent.getIntExtra("jugador_equipo", -1);

        // Mostrar la información en los TextViews
        nombreTextView.setText(nombre != null ? nombre : "Nombre no disponible");
        apellidosTextView.setText(apellidos != null ? apellidos : "Apellidos no disponibles");
        sexoTextView.setText(sexo != null ? sexo : "Sexo no disponible");
        fechaNacimientoTextView.setText(fechaNacimiento != null ? fechaNacimiento : "Fecha de nacimiento no disponible");
        alturaTextView.setText(altura != 0 ? String.valueOf(altura) : "Altura no disponible");
        posicionTextView.setText(posicion != null ? posicion : "Posición no disponible");
        equipoTextView.setText(equipo != -1 ? String.valueOf(equipo) : "Equipo no disponible");


        btnSalir.setOnClickListener(v -> finish()); // Al hacer click, la actividad se cierra
    }
}
