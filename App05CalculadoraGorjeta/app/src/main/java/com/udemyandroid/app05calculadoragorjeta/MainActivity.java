package com.udemyandroid.app05calculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textPorcentagem, textGorjeta, textTotal;
    private SeekBar seekBarPorcentagem;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor = findViewById(R.id.editValor);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textGorjeta = findViewById(R.id.textGorjeta);
        textTotal = findViewById(R.id.textTotal);
        seekBarPorcentagem = findViewById(R.id.seekBar);

        seekBarPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i;
                textPorcentagem.setText( Math.round(porcentagem) + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calcular(){
        String valorRecuperado = editValor.getText().toString();

        if (valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor para a conta",
                    Toast.LENGTH_LONG
            ).show();
        }else{

            double valorDigitado = Double.parseDouble(valorRecuperado);

            double gorjeta = valorDigitado * (porcentagem/100);

            textGorjeta.setText("R$" + gorjeta);

            textTotal.setText("R$"+(gorjeta+valorDigitado));

        }
    }
}