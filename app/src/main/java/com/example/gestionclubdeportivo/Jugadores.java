package com.example.gestionclubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Jugadores extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jugadores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button info = findViewById(R.id.btInfo);
        info.setOnClickListener(view -> mostrarInfo()); //Versión simplificada

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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


    public void mostrarInfo(){
        String texto = "";
        RadioButton masc = findViewById(R.id.radioButtonMasc);
        RadioButton fem = findViewById(R.id.radioButtonFem);
        RadioButton otro = findViewById(R.id.radioButtonOtro);
        //Nombre
        EditText nombre = findViewById(R.id.inputNombre);
        String nombreTexto = nombre.getText().toString();
        //Apellidos
        EditText apellidos = findViewById(R.id.inputApellidos);
        String apellidosTexto = apellidos.getText().toString();
        //Altura
        EditText altura = findViewById(R.id.inputAltura);
        String alturaText = altura.getText().toString();

            if (masc.isChecked()){
                texto = "El jugador "+nombreTexto+" "+apellidosTexto+", mide "+alturaText+" metros";
            }else {
                texto = "La jugadora "+nombreTexto+" "+apellidosTexto+", mide "+alturaText+" metros";
            }

        if (nombreTexto.equals("Nombre") || apellidosTexto.equals("Apellido1 y Apellido2") || alturaText.equals("Altura")){
            Toast.makeText(this,"Debe rellenar el formulario",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,texto,Toast.LENGTH_LONG).show();

        }

    }
}