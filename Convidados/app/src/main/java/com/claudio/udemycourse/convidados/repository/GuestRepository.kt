package com.claudio.udemycourse.convidados.repository

import android.content.ContentValues
import android.content.Context
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import com.claudio.udemycourse.convidados.constants.DataBaseConstants
import com.claudio.udemycourse.convidados.model.GuestModel
import java.lang.Exception

class GuestRepository (context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()

    // Singleton
    /*companion object {
        private lateinit var repository: GuestRepository
        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }*/

    fun insert(guest: GuestModel): Boolean {
        // Nova forma de insert
        return guestDataBase.insert(guest) > 0

        /*try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            print("INSERT EFETUADO COM SUCESSO!")
            Log.d("LOG-OK","INSERT EFETUADO COM SUCESSO!")
            return true
        } catch (e: Exception){
            println("Erro ao inserir no BD: ${e.message}")
            Log.d("LOG-ERROR","Erro ao inserir no BD: ${e.message}")
            return false
        }*/
    }

    fun update(guest: GuestModel): Boolean {

        return guestDataBase.update(guest) > 0

        /*try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)

            Log.d("LOG-OK","UPDATE EFETUADO COM SUCESSO!")
            return true
        } catch ( e: Exception){
            Log.d("LOG-ERROR","Erro ao fazer update no BD: ${e.message}")
            return false
        }*/
    }

    fun delete(id: Int) {
        val guest = get(id)

        if (guest != null) {
            guestDataBase.delete(guest)
        }

        /*try {
            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)

            Log.d("LOG-OK","DELETE EFETUADO COM SUCESSO!")
            return true
        } catch ( e: Exception){
            Log.d("LOG-ERROR","Erro ao fazer delete no BD: ${e.message}")
            return false
        }*/
    }

    fun getAll(): List<GuestModel> {

        return guestDataBase.getAll()

        /*val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                DataBaseConstants.GUEST.COLUMNS.ID
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    val guest = GuestModel(id, name, presence == 1)
                    list.add(guest)
                }
            }

            cursor.close()
            Log.d("LOG-OK","SELECT TODOS COM SUCESSO! - ${list.get(0)}")
        } catch (e: Exception) {
            Log.d("LOG-ERROR","Erro ao fazer SELECT TODOS no BD: ${e.message}")
            return list
        }

        return list*/

    }

    fun get(id: Int): GuestModel? {

        return guestDataBase.get(id)

        /*var guest: GuestModel? = null

        try {
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                DataBaseConstants.GUEST.COLUMNS.ID
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    guest = GuestModel(id, name, presence == 1)
                }
            }

            cursor.close()
            if (guest != null) {
                Log.d("LOG-OK","SELECT TODOS COM SUCESSO! - ${guest.name}")
            } else {
                Log.d("LOG-OK","SELECT TODOS COM ERRO DE CONEXAO / INICIALIZAÇÃO!")
            }
        } catch (e: Exception) {
            Log.d("LOG-ERROR","Erro ao fazer SELECT TODOS no BD: ${e.message}")
            return guest
        }

        return guest*/

    }

    fun getPresent(): List<GuestModel> {

        return guestDataBase.getPresent()

        /*val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT id, name, presence FROM GUEST WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    val guest = GuestModel(id, name, presence == 1)
                    list.add(guest)
                }
            }

            cursor.close()
            Log.d("LOG-OK","SELECT PRESENTES COM SUCESSO!")
        } catch (e: Exception) {
            Log.d("LOG-ERROR","Erro ao fazer SELECT PRESENTES no BD: ${e.message}")
            return list
        }

        return list*/

    }

    fun getAbsent(): List<GuestModel> {

        return guestDataBase.getAbsent()

        /*val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT id, name, presence FROM GUEST WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    val guest = GuestModel(id, name, presence == 1)
                    list.add(guest)
                }
            }

            cursor.close()
            Log.d("LOG-OK","SELECT AUSENTES COM SUCESSO!")
        } catch (e: Exception) {
            Log.d("LOG-ERROR","Erro ao fazer SELECT AUSENTES no BD: ${e.message}")
            return list
        }

        return list*/
    }
}