package com.example.calculagorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextValor;
    private TextView editTextPorcentagem;
    private TextView textViewGorjeta;
    private TextView textViewTotal;
    private SeekBar seekBarGorjeta;

    private double porcent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPorcentagem = findViewById(R.id.editTextPorcentagem);
        textViewGorjeta = findViewById(R.id.textViewGorjeta);
        editTextValor = findViewById(R.id.editTextValor);
        textViewTotal = findViewById(R.id.textViewTotal);
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta);

        // Adicionar listener à seekbar
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcent = progress;
                editTextPorcentagem.setText(Math.round(porcent) + " %");

                calcularGorjetaTotal();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        editTextValor.setOnKeyListener((v, keyCode, event) -> {
            calcularGorjetaTotal();
            return false;
        });
    }

    private void calcularGorjetaTotal() {
        String valorStr = editTextValor.getText().toString();
        if (!valorStr.isEmpty()) {
            double valor = Double.parseDouble(valorStr);
            double gorjeta = (valor * porcent) / 100;
            double total = valor + gorjeta;

            textViewGorjeta.setText("Gorjeta: R$" + gorjeta);
            textViewTotal.setText("Total: R$" + total);
        }
    }
}
