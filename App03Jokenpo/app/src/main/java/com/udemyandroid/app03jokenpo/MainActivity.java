package com.udemyandroid.app03jokenpo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selecionadoPedra(View view){
        this.opcaoSelecionada("pedra");
    }

    public void selecionadoPapel(View view){
        this.opcaoSelecionada("papel");
    }

    public void selecionadoTesoura(View view){
        this.opcaoSelecionada("tesoura");
    }

    public void opcaoSelecionada(String escolhaUsuario){
        ImageView imageResultadoApp = findViewById(R.id.imageResultadoApp);
        TextView textoResultado = findViewById(R.id.textResultado);
        ConstraintLayout layout = findViewById(R.id.backColor);

        int numero = new Random().nextInt(3);

        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[numero];

        switch ( opcaoApp ){
            case "pedra":
                imageResultadoApp.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imageResultadoApp.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imageResultadoApp.setImageResource(R.drawable.tesoura);
                break;
            default:
                imageResultadoApp.setImageResource(R.drawable.padrao);
        }

        if(
           (opcaoApp == "tesoura" && escolhaUsuario == "papel") ||
           (opcaoApp == "papel" && escolhaUsuario == "pedra") ||
           (opcaoApp == "pedra" && escolhaUsuario == "tesoura")

        ){
            textoResultado.setText("Você Perdeu. Aplicativo Ganhou com " + opcaoApp + ".");
            layout.setBackgroundColor(Color.RED);
        }else if(
           (escolhaUsuario == "tesoura" && opcaoApp == "papel") ||
           (escolhaUsuario == "papel" && opcaoApp == "pedra") ||
           (escolhaUsuario == "pedra" && opcaoApp == "tesoura")
        ){
            textoResultado.setText("Você Ganhou. Jogador Ganhou com " + escolhaUsuario + ".");
            layout.setBackgroundColor(Color.GREEN);
        }else{
            textoResultado.setText("Empate com a opção " + escolhaUsuario + ".");
            layout.setBackgroundColor(Color.BLUE);
        }
    }
}