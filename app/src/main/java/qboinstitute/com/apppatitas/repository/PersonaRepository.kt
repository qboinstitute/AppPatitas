package qboinstitute.com.apppatitas.repository

import androidx.lifecycle.LiveData
import qboinstitute.com.apppatitas.db.dao.PersonaDao
import qboinstitute.com.apppatitas.db.entity.PersonaEntity

class PersonaRepository(private val personaDao: PersonaDao) {

    suspend fun insertar(personaEntity: PersonaEntity){
        personaDao.insertar(personaEntity)
    }

    suspend fun actualizar(personaEntity: PersonaEntity){
        personaDao.actualizar(personaEntity)
    }

    suspend fun eliminartodo(){
        personaDao.eliminartodo()
    }

    fun obtener(): LiveData<PersonaEntity>{
        return personaDao.obtener()
    }
}