package com.udemyandroid.app04alcoolgasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editarPrecoAlcool, editarPrecoGasolina;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editarPrecoAlcool = findViewById(R.id.precoAlcool);
        editarPrecoGasolina = findViewById(R.id.precoGasolina);

        textoResultado = findViewById(R.id.textResultado);
    }

    public void calcularPreco(View view){

        String precoAlcool = editarPrecoAlcool.getText().toString();
        String precoGasolina = editarPrecoGasolina.getText().toString();

        Boolean resultadoTeste = validarCampos(precoAlcool, precoGasolina);
        if (resultadoTeste){
            Double valorAlcool = Double.parseDouble( precoAlcool );
            Double valorGasolina = Double.parseDouble( precoGasolina );
            Double valorCalculado;

            valorCalculado = valorAlcool / valorGasolina;

            if (valorCalculado >= 0.7){
                textoResultado.setText("Gasolina Sairia Mais em Conta");
            }else{
                textoResultado.setText("Alcool Sairia Mais em Conta");
            }

        }
    }

    public Boolean validarCampos(String pAlcool, String pGasolina){

        Boolean camposValidos = true;
        String campoInvalido = "Resultado";

        if ( (pAlcool == null || pAlcool.equals("")) && (pGasolina == null || pGasolina.equals(""))){
            camposValidos = false;
            campoInvalido = "Preencha ambos os campos";
        } else  if( pAlcool == null || pAlcool.equals("") ){
            camposValidos = false;
            campoInvalido = "Preencha campo Alcool";
        }else if (pGasolina == null || pGasolina.equals("")){
            camposValidos = false;
            campoInvalido = "Preencha campo Gasolina";
        }

        textoResultado.setText(campoInvalido);

        return camposValidos;
    }

}