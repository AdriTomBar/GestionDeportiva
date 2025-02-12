package com.example.gestionclubdeportivo.Actividades;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.gestionclubdeportivo.Adapters.EquipoAdapter;
import com.example.gestionclubdeportivo.R;
import com.example.gestionclubdeportivo.Database.AppDatabase;
import com.example.gestionclubdeportivo.models.Equipo;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Equipos extends AppCompatActivity {

    private Spinner spinnerCategoria, spinnerModalidad;
    private EquipoAdapter equipoAdapter;
    private AppDatabase db;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);

        // Inicializar la base de datos
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "gestionclubdeportivo")
                .fallbackToDestructiveMigration()
                .build();

        // Inicializar los hilos, en este caso se crea un solo hilo
        executorService = Executors.newSingleThreadExecutor();

        // Inicializar Spinners
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        spinnerModalidad = findViewById(R.id.spinnerModalidad);

        // Crear un ArrayAdapter para el Spinner de Categoría usando los recursos de strings
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(
                this,
                R.array.categorias, // Array de strings definido
                android.R.layout.simple_spinner_item // Layout predeterminado para los elementos del Spinner
        );

        // Definir el diseño que se usará para el desplegable del Spinner
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner de Categoría
        spinnerCategoria.setAdapter(adapterCategoria);

        ArrayAdapter<CharSequence> adapterModalidad = ArrayAdapter.createFromResource(
                this,
                R.array.modalidades,
                android.R.layout.simple_spinner_item
        );

        // Definir el diseño que se usará para el desplegable del Spinner
        adapterModalidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerModalidad.setAdapter(adapterModalidad);

        // Inicializar el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEquipos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cargar la informacion y crear nuevos equipos si la BD esta vacia
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Equipo> equipos = db.equipoDao().getAllEquipos();

                if (equipos.isEmpty()) {

                    Drawable drawable = ContextCompat.getDrawable(Equipos.this, R.drawable.aguilas_escudo);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
                    Equipo equipo1 = new Equipo("Aguilas FC", "Nike", "Escuela", "Masculino", false, byteArrayImage, "Aguilas FC Contacto", "644756345", "Lunes 10", "19:00", "Lunes y Jueves");



                     drawable = ContextCompat.getDrawable(Equipos.this, R.drawable.patinio_escudo);
                     bitmap = ((BitmapDrawable) drawable).getBitmap();
                     byteArrayOutputStream = new ByteArrayOutputStream();
                     bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                     byteArrayImage = byteArrayOutputStream.toByteArray();
                     Equipo equipo2 = new Equipo("Patiño FC", "Adidas", "Juvenil", "Femenino", true, byteArrayImage, "Patiño FC Contacto", "644756345", "Lunes 13", "13:00", "Martes y Viernes");

                    drawable = ContextCompat.getDrawable(Equipos.this, R.drawable.real_murcia);
                    bitmap = ((BitmapDrawable) drawable).getBitmap();
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byteArrayImage = byteArrayOutputStream.toByteArray();
                    Equipo equipo3 = new Equipo("Real Murcia FC", "Adidas", "Senior", "Femenino", true, byteArrayImage, "Real Murcia FC Contacto", "675544655", "Miercoles 4", "09:00", "Martes");



                    db.equipoDao().insertAll(equipo1, equipo2, equipo3);



                    // Recarga la lista con los equipos creados hasta ahora
                    equipos = db.equipoDao().getAllEquipos();
                }

                List<Equipo> finalEquipos = equipos;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        equipoAdapter = new EquipoAdapter(Equipos.this, finalEquipos);
                        recyclerView.setAdapter(equipoAdapter);
                    }
                });
            }
        });

        // FIltrar equipos por categoria y modalidad
        AdapterView.OnItemSelectedListener filtroListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
