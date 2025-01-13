package com.example.gestionclubdeportivo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Jugadores extends AppCompatActivity {

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