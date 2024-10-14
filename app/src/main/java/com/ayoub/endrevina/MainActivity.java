package com.ayoub.endrevina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int numeroAEndevinar;
    private int intents;
    private TextView historialTextView;
    private EditText inputEditText;
    private TextView intentsTextView;
    private Random random;
    public static List<String> intentsGuardats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        random = new Random();
        numeroAEndevinar = generarNumeroAleatori();
        intents = 0;
        intentsGuardats = new ArrayList<>();
        inputEditText = findViewById(R.id.inputNumero);
        historialTextView = findViewById(R.id.historial);
        intentsTextView = findViewById(R.id.intents);
        Button btnValidar = findViewById(R.id.btnValidar);
        Button btnHallOfFame = findViewById(R.id.btnHallOfFame);

        btnValidar.setOnClickListener(v -> validarNumero());
        btnHallOfFame.setOnClickListener(v -> obrirAlHallOfFame());
    }

    private int generarNumeroAleatori() {
        return random.nextInt(100) + 1;
    }

    private void validarNumero() {
        String input = inputEditText.getText().toString();
        if (!input.isEmpty()) {
            int numeroIntrodut = Integer.parseInt(input);
            intents++;
            intentsTextView.setText("Intents: " + intents);
            if (numeroIntrodut == numeroAEndevinar) {
                mostrarFiDePartida();
            } else {
                if (numeroIntrodut < numeroAEndevinar) {
                    Toast.makeText(this, "El número és més gran", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "El número és més petit", Toast.LENGTH_SHORT).show();
                }
                actualitzarHistorial(numeroIntrodut);
                inputEditText.setText("");
            }
        } else {
            Toast.makeText(this, "Introdueix un número", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarFiDePartida() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Felicitats!")
                .setMessage("Has endevinat el número en " + intents + " intents.\nVols guardar l'intent?")
                .setPositiveButton("Sí", (dialog, which) -> preguntarNomJugador())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void preguntarNomJugador() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText inputNom = new EditText(this);
        inputNom.setHint("Introdueix el teu nom");

        builder.setTitle("Guardar Intent")
                .setMessage("Escriu el teu nom per guardar l'intent:")
                .setView(inputNom)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    String nomJugador = inputNom.getText().toString();
                    if (!nomJugador.isEmpty()) {
                        intentsGuardats.add(nomJugador + " - " + intents + " intents");
                        mostrarOpcioHallOfFame();
                    } else {
                        Toast.makeText(this, "Introdueix un nom per guardar l'intent.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel·lar", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void mostrarOpcioHallOfFame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vols anar al Hall of Fame?")
            .setPositiveButton("Sí", (dialog, which) -> obrirAlHallOfFame())
            .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
            .show();
}

    private void actualitzarHistorial(int numeroIntrodut) {
        String textHistorial = historialTextView.getText().toString();
        textHistorial += "Intent " + intents + ": " + numeroIntrodut + "\n";
        historialTextView.setText(textHistorial);
    }

    private void obrirAlHallOfFame() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        startActivity(intent);
    }




}




