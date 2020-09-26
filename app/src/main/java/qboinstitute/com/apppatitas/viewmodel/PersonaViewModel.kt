package qboinstitute.com.apppatitas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import qboinstitute.com.apppatitas.db.PatitasRoomDatabase
import qboinstitute.com.apppatitas.db.entity.PersonaEntity
import qboinstitute.com.apppatitas.repository.PersonaRepository

class PersonaViewModel (application: Application):
AndroidViewModel(application)
{
    private val repository: PersonaRepository

    init {
        val personaDao = PatitasRoomDatabase.getDatabase(application)
            .personaDao()
        repository = PersonaRepository(personaDao)
    }

    fun insertar(personaEntity: PersonaEntity) = viewModelScope
        .launch(Dispatchers.IO){
            repository.insertar(personaEntity)
        }

    fun actualizar(personaEntity: PersonaEntity) = viewModelScope
        .launch(Dispatchers.IO){
            repository.actualizar(personaEntity)
        }

    fun eliminartodo() = viewModelScope
        .launch(Dispatchers.IO){
            repository.eliminartodo()
        }

    fun obtener(): LiveData<PersonaEntity>{
        return  repository.obtener()
    }
}