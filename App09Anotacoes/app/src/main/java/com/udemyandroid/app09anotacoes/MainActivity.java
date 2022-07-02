package com.udemyandroid.app09anotacoes;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.udemyandroid.app09anotacoes.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private  Anotacoes anotacoes;
    private EditText editAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editAnotacao = findViewById(R.id.editAnotacao);
        anotacoes = new Anotacoes(getApplicationContext());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoApp = editAnotacao.getText().toString();
                if (textoApp.equals("")){
                    Snackbar.make(view, "Insira alguma texto na área de anotações!", Snackbar.LENGTH_LONG)
                            .show();
                }else{
                    anotacoes.salvarAnotacao(textoApp);
                    Snackbar.make(view, "Anotação Salva com Sucesso!", Snackbar.LENGTH_LONG)
                            .show();
                }


            }
        });

        String anotacaoRecuperada = anotacoes.recuperarAnotacao();
        if ( !anotacaoRecuperada.equals("")){
            editAnotacao.setText(anotacaoRecuperada);
        }
    }

}