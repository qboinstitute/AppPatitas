package qboinstitute.com.apppatitas.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import qboinstitute.com.apppatitas.db.dao.PersonaDao
import qboinstitute.com.apppatitas.db.entity.PersonaEntity


@Database(entities = [PersonaEntity::class], version = 1)
abstract  class PatitasRoomDatabase : RoomDatabase() {

    abstract fun personaDao() : PersonaDao

    companion object{

        @Volatile
        private var INSTANCE: PatitasRoomDatabase? = null

        fun getDatabase(context: Context): PatitasRoomDatabase{
            val temporalInstance = INSTANCE
            if(temporalInstance != null){
                return temporalInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PatitasRoomDatabase::class.java,
                    "patitasdb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}