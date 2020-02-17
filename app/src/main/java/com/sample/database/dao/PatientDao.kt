package com.sample.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.sample.database.model.Patient

@Dao
interface PatientDao {
    @Query("SELECT * from patient")
    fun getPatients(): LiveData<List<Patient>>
}