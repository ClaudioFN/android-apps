package com.udemyandroid.app02frases;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gerarNovaFrase(View view){

        String [] frases = {
                "As pessoas costumam dizer que a motivação não dura sempre. Bem, nem o efeito do banho, por isso recomenda-se diariamente.",
                "Toda ação humana, quer se torne positiva ou negativa, precisa depender de motivação.",
                "No meio da dificuldade encontra-se a oportunidade.",
                "Eu faço da dificuldade a minha motivação. A volta por cima vem na continuação.",
                "É parte da cura o desejo de ser curado."};

        int numero = new Random().nextInt(5); // 0, 1, 2, 3, 4
        TextView texto = findViewById(R.id.textResultado);
        texto.setText(frases[numero]);
    }
}