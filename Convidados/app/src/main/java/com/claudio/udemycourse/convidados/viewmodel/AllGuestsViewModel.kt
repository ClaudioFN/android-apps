package com.claudio.udemycourse.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.claudio.udemycourse.convidados.model.GuestModel
import com.claudio.udemycourse.convidados.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: GuestRepository = GuestRepository.getInstance(application.applicationContext)
    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll() {
        listAllGuests.value = repository.getAll()
    }
}