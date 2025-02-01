package com.example.gestionclubdeportivo.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionclubdeportivo.R;

public class InfoEquipo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_equipo);

        // Obtener referencias a los elementos del layout
        ImageView imgEquipo = findViewById(R.id.imgEquipoDetalle);
        TextView tvNombre = findViewById(R.id.tvNombreDetalle);
        TextView tvPatrocinador = findViewById(R.id.tvPatrocinadorDetalle);
        TextView tvCategoria = findViewById(R.id.tvCategoriaDetalle);
        TextView tvModalidad = findViewById(R.id.tvModalidadDetalle);
        TextView tvFederado = findViewById(R.id.tvFederadoDetalle);
        TextView tvDiaPartido = findViewById(R.id.tvDiaPartidoDetalle);
        TextView tvHoraPartido = findViewById(R.id.tvHoraPartidoDetalle);
        TextView tvEntrenamientos = findViewById(R.id.tvEntrenamientosDetalle);
        TextView tvContacto = findViewById(R.id.tvContactoDetalle);
        TextView tvTelefono = findViewById(R.id.tvTelefonoDetalle);

        Button btSalir = findViewById(R.id.btSalir);
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Obtener datos del intent
        Intent intent = getIntent();

        if (intent != null) {
            imgEquipo.setImageResource(intent.getIntExtra("imagen", 0));
            tvNombre.setText(getString(R.string.nombre) + ": " + intent.getStringExtra("nombre"));
            tvPatrocinador.setText(getString(R.string.patrocinador) + ": " + intent.getStringExtra("patrocinador"));
            tvCategoria.setText(getString(R.string.categoria) + ": " + intent.getStringExtra("categoria"));
            tvModalidad.setText(getString(R.string.modalidad) + ": " + intent.getStringExtra("modalidad"));
            tvFederado.setText(getString(R.string.federado) + ": " + (intent.getBooleanExtra("federado", false) ? "Sí" : "No"));
            tvDiaPartido.setText(getString(R.string.dia_partido) + ": " + intent.getStringExtra("dia_partido"));
            tvHoraPartido.setText(getString(R.string.hora_partido) + ": " + intent.getStringExtra("hora_partido"));
            tvEntrenamientos.setText(getString(R.string.entrenamientos) + ": " + intent.getStringExtra("entrenamientos"));
            tvContacto.setText(getString(R.string.persona_contacto) + ": " + intent.getStringExtra("persona_contacto"));
            tvTelefono.setText(getString(R.string.numero_contacto) + ": " + intent.getStringExtra("numero_contacto"));
        }
    }
}
