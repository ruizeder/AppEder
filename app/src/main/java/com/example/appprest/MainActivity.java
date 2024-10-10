package com.example.appprest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etMonto;
    private EditText etTasa;
    private EditText etPlazo;
    private TextView teVRes;
    private TextView teVInversion;
    private TextView teVGanancias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicialización de componentes
        etMonto = findViewById(R.id.etMonto);
        etTasa = findViewById(R.id.etTasa);
        etPlazo = findViewById(R.id.etPlazo);
        teVInversion = findViewById(R.id.teVInversion);
        teVGanancias = findViewById(R.id.teVGanancias);
        Button btnCalcular = findViewById(R.id.btnCalcular);

        // Configuración del evento OnClick para el botón
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularRendimiento();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calcularRendimiento() {
        // Validar entradas
        if (etMonto.getText().toString().isEmpty() || etTasa.getText().toString().isEmpty() || etPlazo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener valores de entrada
        double montoInvertido = Double.parseDouble(etMonto.getText().toString());
        double tasaRendimiento = Double.parseDouble(etTasa.getText().toString()) / 100; // Convertir a decimal
        int plazo = Integer.parseInt(etPlazo.getText().toString());

        // Calcular retorno de inversión
        double totalObtenido = montoInvertido * Math.pow((1 + tasaRendimiento), plazo);
        double beneficioObtenido = totalObtenido - montoInvertido;

        // Mostrar resultados
        teVInversion.setText(String.format("Total: %.2f", totalObtenido));
        teVGanancias.setText(String.format("Ganancias: %.2f", beneficioObtenido));
    }
}
