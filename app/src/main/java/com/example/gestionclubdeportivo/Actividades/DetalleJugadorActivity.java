package com.example.gestionclubdeportivo.Actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionclubdeportivo.DAOs.EquipoDao;
import com.example.gestionclubdeportivo.DAOs.DaoJugador;
import com.example.gestionclubdeportivo.Database.AppDatabase;
import com.example.gestionclubdeportivo.R;
import com.example.gestionclubdeportivo.models.Equipo;
import com.example.gestionclubdeportivo.models.Jugador;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

public class DetalleJugadorActivity extends AppCompatActivity {

    private TextView nombreTextView;
    private TextView apellidosTextView;
    private TextView sexoTextView;
    private TextView fechaNacimientoTextView;
    private TextView alturaTextView;
    private TextView posicionTextView;
    private TextView equipoTextView;
    private Button btnSalir;

    private static final int PICK_IMAGE = 1;
    private ImageView imageViewJugador;
    private Button btnSeleccionarFoto;
    private Jugador jugador;
    private DaoJugador daoJugador;
    private EquipoDao daoEquipo;
    private AppDatabase db;
    private int jugadorId;
    private Spinner spinnerEquipos;
    private List<Equipo> listaEquipos;
    private ArrayAdapter<Equipo> equipoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_jugador);

        // Inicializar vistas
        nombreTextView = findViewById(R.id.nombreTextViewDetalle);
        apellidosTextView = findViewById(R.id.apellidosTextViewDetalle);
        sexoTextView = findViewById(R.id.sexoTextViewDetalle);
        fechaNacimientoTextView = findViewById(R.id.fechaNacimientoTextViewDetalle);
        alturaTextView = findViewById(R.id.alturaTextViewDetalle);
        posicionTextView = findViewById(R.id.posicionTextViewDetalle);
        equipoTextView = findViewById(R.id.equipoTextViewDetalle);
        btnSalir = findViewById(R.id.btnSalir);
        imageViewJugador = findViewById(R.id.imgJugador);
        btnSeleccionarFoto = findViewById(R.id.btFoto);
        spinnerEquipos = findViewById(R.id.spinnerEquipos);

        // Inicializar la base de datos
        db = AppDatabase.getInstance(getApplicationContext());
        daoJugador = db.jugadorDao();
        daoEquipo = db.equipoDao();

        // Obtener datos del Intent
        Intent intent = getIntent();
        jugadorId = intent.getIntExtra("jugador_id", -1);
        String nombre = intent.getStringExtra("jugador_nombre");
        String apellidos = intent.getStringExtra("jugador_apellidos");
        String sexo = intent.getStringExtra("jugador_sexo");
        String fechaNacimiento = intent.getStringExtra("jugador_fecha_nacimiento");
        float altura = intent.getFloatExtra("jugador_altura", 0);
        String posicion = intent.getStringExtra("jugador_posicion");
        int equipoId = intent.getIntExtra("jugador_equipo", -1);

        // Mostrar datos en los TextViews
        nombreTextView.setText(nombre != null ? nombre : "Nombre no disponible");
        apellidosTextView.setText(apellidos != null ? apellidos : "Apellidos no disponibles");
        sexoTextView.setText(sexo != null ? sexo : "Sexo no disponible");
        fechaNacimientoTextView.setText(fechaNacimiento != null ? fechaNacimiento : "Fecha de nacimiento no disponible");
        alturaTextView.setText(altura != 0 ? String.valueOf(altura) : "Altura no disponible");
        posicionTextView.setText(posicion != null ? posicion : "Posición no disponible");

        // Obtener los equipos de la base de datos y rellenar spinner
        Executors.newSingleThreadExecutor().execute(() -> {
            listaEquipos = daoEquipo.getAllEquipos(); // Obtener equipos
            runOnUiThread(() -> {
                equipoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaEquipos);
                equipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerEquipos.setAdapter(equipoAdapter);

                // Seleccionar el equipo del jugador actual
                if (equipoId != -1) {
                    int equipoPosicion = obtenerPosicionEquipo(equipoId);
                    spinnerEquipos.setSelection(equipoPosicion);
                }
            });
        });

        // Cargar los datos del jugador desde la base de datos
        Executors.newSingleThreadExecutor().execute(() -> {
            jugador = daoJugador.getJugadorById(jugadorId);
            if (jugador != null && jugador.getImagen() != null) {
                runOnUiThread(() -> {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(jugador.getImagen(), 0, jugador.getImagen().length);
                    imageViewJugador.setImageBitmap(bitmap);
                });
            }
        });

        // Botón para seleccionar imagen
        btnSeleccionarFoto.setOnClickListener(v -> abrirGaleria());

        // Lógica cuando se selecciona un equipo
        spinnerEquipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Equipo equipoSeleccionado = (Equipo) parentView.getItemAtPosition(position);
                if (jugador != null) {
                    jugador.setEquipo(equipoSeleccionado.getId());
                    equipoTextView.setText(equipoSeleccionado.getNombre());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada si no se selecciona ningún equipo
            }
        });

        // Guardar cambios al salir
        btnSalir.setOnClickListener(v -> {
            guardarImagen();
            finish();
        });
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imagenSeleccionada = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagenSeleccionada);
                imageViewJugador.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void guardarImagen() {
        Bitmap bitmap = ((BitmapDrawable) imageViewJugador.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imagenBytes = stream.toByteArray();

        if (jugador != null) {
            jugador.setImagen(imagenBytes);
            Executors.newSingleThreadExecutor().execute(() -> {
                daoJugador.update(jugador);
                Log.d("DetalleJugadorActivity", "Imagen guardada en la base de datos");
            });
        }
    }

    private int obtenerPosicionEquipo(int equipoId) {
        for (int i = 0; i < listaEquipos.size(); i++) {
            if (listaEquipos.get(i).getId() == equipoId) {
                return i;
            }
        }
        return -1; // Si no se encuentra el equipo, se selecciona el primer elemento por defecto
    }
}
