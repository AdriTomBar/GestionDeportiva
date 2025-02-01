package com.example.gestionclubdeportivo.Actividades;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.gestionclubdeportivo.R;
import com.example.gestionclubdeportivo.models.Jugador;
import com.example.gestionclubdeportivo.Database.AppDatabase;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class inscripcionJugador extends AppCompatActivity {
    Toolbar toolbar;
    private TextInputEditText inputFecha;
    private Calendar calendar;
    private Spinner spinnerPosicion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inscipcion_jugador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btCancelar = findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });

        Button btListaJugadores = findViewById(R.id.btListaJugadores);
        btListaJugadores.setOnClickListener(v -> {
            Intent intent = new Intent(inscripcionJugador.this, ListaJugadoresActivity.class);
            startActivity(intent);
        });

        // Inicializar el calendario
        calendar = Calendar.getInstance();

        // Inicializar el campo de fecha
        inputFecha = findViewById(R.id.inputFecha);

        // Inicializar el spinner de posición
        spinnerPosicion = findViewById(R.id.spinnerPosicion);

        // Configurar el DatePicker
        setupDatePicker();
    }

    private void atras(){
        finish();
    }

    private void setupDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateDateInView();
        };

        // Configurar el click listener para el campo de fecha
        inputFecha.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );

            // Establecer la fecha máxima como hoy
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

            datePickerDialog.show();
        });
    }

    private void updateDateInView() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        inputFecha.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Mostrar el menú definido en menu.xml
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.jugadores) {
            Intent intentJugadores = new Intent(this, inscripcionJugador.class);
            startActivity(intentJugadores);
            return true;
        }
        else if (itemId == R.id.equipos) {
            Intent intentEquipos = new Intent(this, Equipos.class);
            startActivity(intentEquipos);
            return true;
        }
        else if (itemId == R.id.informes) {
            Intent intentInformes = new Intent(this, Informes.class);
            startActivity(intentInformes);
            return true;
        }
        else if (itemId == R.id.acercaDe) {
            Intent intentAcercaDe = new Intent(this, AcercaDe.class);
            startActivity(intentAcercaDe);
            return true;
        }
        else if (itemId == R.id.salir) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void mostrarInfo() {
        // Obtener los datos de los campos de texto y radio buttons
        String nombreTexto = ((EditText) findViewById(R.id.inputNombre)).getText().toString();
        String apellidosTexto = ((EditText) findViewById(R.id.inputApellidos)).getText().toString();
        String alturaText = ((EditText) findViewById(R.id.inputAltura)).getText().toString();
        String fechaNacimiento = inputFecha.getText().toString();

        // Determinar el sexo seleccionado
        String sexo = "Otro";
        if (((RadioButton) findViewById(R.id.radioButtonMasc)).isChecked()) {
            sexo = "Masculino";
        } else if (((RadioButton) findViewById(R.id.radioButtonFem)).isChecked()) {
            sexo = "Femenino";
        }

        // Validar si los campos son válidos
        if (nombreTexto.isEmpty() || apellidosTexto.isEmpty() || alturaText.isEmpty() || fechaNacimiento.isEmpty()) {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
            return;
        }

        // Convertir altura a float
        float altura = Float.parseFloat(alturaText);

        // Obtener la posición seleccionada
        String posicion = spinnerPosicion.getSelectedItem().toString();

        // Crear una instancia del jugador con los datos obtenidos
        Jugador nuevoJugador = new Jugador(nombreTexto, apellidosTexto, sexo, fechaNacimiento, altura, posicion, 0); // equipoId = 0 for unassigned

        // Insertar el jugador en la base de datos
        insertJugadorEnBaseDeDatos(nuevoJugador);
    }

    private void insertJugadorEnBaseDeDatos(Jugador jugador) {
        // Crear o obtener la base de datos
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "jugadores_db")
                .allowMainThreadQueries()  // NO hacer esto en producción, usar hilos en segundo plano
                .build();

        // Insertar el jugador en la base de datos
        db.jugadorDao().insert(jugador);

        // Confirmar que se insertó correctamente
        Toast.makeText(this, "Jugador registrado con éxito", Toast.LENGTH_SHORT).show();
    }
}