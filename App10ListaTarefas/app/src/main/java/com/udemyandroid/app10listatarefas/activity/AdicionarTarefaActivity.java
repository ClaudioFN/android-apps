package com.udemyandroid.app10listatarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.udemyandroid.app10listatarefas.R;
import com.udemyandroid.app10listatarefas.helper.TarefaDAO;
import com.udemyandroid.app10listatarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);

        // Recuperar tarefa para edicao
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        // Registrar na caixa de texto
        if (tarefaAtual != null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSalvar:
                TarefaDAO tarefaDAO = new TarefaDAO( getApplicationContext() );
                if (tarefaAtual != null){
                    String nomeTarefa = editTarefa.getText().toString();
                    if(!nomeTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());

                        if (tarefaDAO.atualizar( tarefa )){
                            finish();
                            Toast.makeText(getApplicationContext()
                                    , "Tarefa Atualizada com Sucesso!"
                                    , Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext()
                                    , "Erro ao Atualizar Tarefa."
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    String nomeTarefa = editTarefa.getText().toString();
                    if(!nomeTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        if (tarefaDAO.salvar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext()
                                    , "Tarefa Salva com Sucesso!"
                                    , Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext()
                                    , "Erro ao Salvar Tarefa."
                                    , Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}