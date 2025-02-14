package com.example.gestionclubdeportivo.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gestionclubdeportivo.R;

public class ReporteFragment extends Fragment {

    private EditText editTextReporte;
    private Button buttonEnviar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reporte, container, false);

        editTextReporte = view.findViewById(R.id.editTextReporte);
        buttonEnviar = view.findViewById(R.id.buttonEnviar);

        buttonEnviar.setOnClickListener(v -> enviarReporte());

        return view;
    }

    private void enviarReporte() {
        String mensaje = editTextReporte.getText().toString().trim();
        if (mensaje.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, ingrese un mensaje", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822"); // garantiza que solo se abran apps de correo electronico
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"soporteCDIesCierva@ejemplo.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Reporte de usuario");
        intent.putExtra(Intent.EXTRA_TEXT, mensaje);

        try {
            startActivity(Intent.createChooser(intent, "Enviar correo"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "No hay clientes de correo instalados.", Toast.LENGTH_SHORT).show();
        }
    }
}