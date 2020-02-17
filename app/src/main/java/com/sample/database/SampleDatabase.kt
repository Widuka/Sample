package com.sample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.database.dao.PatientDao
import com.sample.database.model.Patient

@Database(entities = [Patient::class], version = 1)
abstract class SampleDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao

    companion object {
        @Volatile
        private var instance: SampleDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(SampleDatabase::class) {
            instance ?: Room.databaseBuilder(
                context,
                SampleDatabase::class.java,
                "sample.db"
            ).build().also { instance = it }
        }
    }
}