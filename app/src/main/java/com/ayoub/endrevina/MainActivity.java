package com.ayoub.endrevina;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int numeroAEndevinar;
    private int intents;
    private TextView historialTextView;
    private EditText inputEditText;
    private TextView intentsTextView;
    private Random random;

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

    private void reiniciarPartida() {
        numeroAEndevinar = generarNumeroAleatori();
        intents = 0;
        historialTextView.setText("");
        intentsTextView.setText("Intents: " + intents);
    }

    private void actualitzarHistorial(int numeroIntrodut) {
        String textHistorial = historialTextView.getText().toString();
        textHistorial += "Intent " + intents + ": " + numeroIntrodut + "\n";
        historialTextView.setText(textHistorial);
    }
}