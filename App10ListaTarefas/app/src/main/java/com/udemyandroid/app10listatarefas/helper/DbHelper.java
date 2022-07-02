package com.udemyandroid.app10listatarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "taferas";


    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL); ";
        try{
            db.execSQL(sql);
            Log.i("INFO DB","Tabela " + TABELA_TAREFAS + " criada");
        }catch (Exception e){
            Log.i("INFOR DB", "Erro ao criar a tabela " + TABELA_TAREFAS + ": " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = " DROP TABLE IF EXISTS " + TABELA_TAREFAS + " ;";
        try{
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB","Tabela " + TABELA_TAREFAS + " criada novamente");
        }catch (Exception e){
            Log.i("INFOR DB", "Erro ao criar a tabela " + TABELA_TAREFAS + ": " + e.getMessage());
        }
    }
}
