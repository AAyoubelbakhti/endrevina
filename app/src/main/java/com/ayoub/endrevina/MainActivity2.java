package com.ayoub.endrevina;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {


    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tableLayout = findViewById(R.id.tableLayout);
        List<String> intentsGuardats = MainActivity.intentsGuardats;


        if (intentsGuardats != null) {
            mostrarIntentsGuardats(intentsGuardats);
        }
    }

    private void mostrarIntentsGuardats(List<String> intentsGuardats) {
        TableRow headerRow = new TableRow(this);
        TextView headerJugador = new TextView(this);
        headerJugador.setText("Jugador");
        TextView headerIntents = new TextView(this);
        headerIntents.setText("Intents");

        headerRow.addView(headerJugador);
        headerRow.addView(headerIntents);
        tableLayout.addView(headerRow);

        for (String intent : intentsGuardats) {
            String[] parts = intent.split(" - ");
            String nomJugador = parts[0];
            String numIntents = parts[1];

            TableRow row = new TableRow(this);
            TextView textViewJugador = new TextView(this);
            textViewJugador.setText(nomJugador);
            TextView textViewIntents = new TextView(this);
            textViewIntents.setText(numIntents);

            row.addView(textViewJugador);
            row.addView(textViewIntents);
            tableLayout.addView(row);



        }
    }
}
