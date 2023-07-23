package com.claudio.udemycourse.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.claudio.udemycourse.convidados.model.GuestModel
import com.claudio.udemycourse.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest

    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            if (repository.insert(guest)) {
                _saveGuest.value = "Sucesso ao incluir convidado!"
            } else {
                _saveGuest.value = ""
            }

        } else {
            if (repository.update(guest)) {
                _saveGuest.value = "Sucesso ao atualizar convidado!"
            } else {
                _saveGuest.value = ""
            }
        }

    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }

}