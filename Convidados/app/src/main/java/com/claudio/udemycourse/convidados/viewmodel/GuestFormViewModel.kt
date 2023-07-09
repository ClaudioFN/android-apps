package com.claudio.udemycourse.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.claudio.udemycourse.convidados.model.GuestModel
import com.claudio.udemycourse.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }

}