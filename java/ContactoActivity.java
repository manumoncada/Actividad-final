package com.example.contactoapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Pantalla "Contacto": formulario + envio con JavaMail
public class ContactoActivity extends AppCompatActivity {

    private TextInputLayout tilNombre, tilCorreo, tilMensaje;
    private TextInputEditText etNombre, etCorreo, etMensaje;
    private Button btnEnviar;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tilNombre = findViewById(R.id.tilNombre);
        tilCorreo = findViewById(R.id.tilCorreo);
        tilMensaje = findViewById(R.id.tilMensaje);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etMensaje = findViewById(R.id.etMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(v -> enviarComentario());
    }

    private void enviarComentario() {
        final String nombre = etNombre.getText().toString().trim();
        final String correo = etCorreo.getText().toString().trim();
        final String mensaje = etMensaje.getText().toString().trim();

        tilNombre.setError(null);
        tilCorreo.setError(null);
        tilMensaje.setError(null);

        boolean valido = true;
        if (nombre.isEmpty()) {
            tilNombre.setError("Escribe tu nombre");
            valido = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilCorreo.setError("Escribe un correo válido");
            valido = false;
        }
        if (mensaje.isEmpty()) {
            tilMensaje.setError("Escribe tu mensaje");
            valido = false;
        }
        if (!valido) return;

        if (ConfigCorreo.CLAVE_APP.equals("TU_CLAVE_DE_APLICACION")) {
            Toast.makeText(this, "Configura ConfigCorreo.java antes de enviar", Toast.LENGTH_LONG).show();
            return;
        }

        btnEnviar.setEnabled(false);
        Toast.makeText(this, "Enviando...", Toast.LENGTH_SHORT).show();

        // El envio de red debe hacerse fuera del hilo principal
        executor.execute(() -> {
            try {
                EmailSender.enviar(nombre, correo, mensaje);
                mainHandler.post(() -> {
                    Toast.makeText(this, "Comentario enviado", Toast.LENGTH_LONG).show();
                    etNombre.setText("");
                    etCorreo.setText("");
                    etMensaje.setText("");
                    btnEnviar.setEnabled(true);
                });
            } catch (Exception e) {
                mainHandler.post(() -> {
                    Toast.makeText(this, "No se pudo enviar: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    btnEnviar.setEnabled(true);
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
