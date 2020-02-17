package com.sample.database.repository

import androidx.lifecycle.LiveData
import com.sample.database.dao.PatientDao
import com.sample.database.model.Patient

class PatientRepository(patientDao: PatientDao){
    val patients: LiveData<List<Patient>> = patientDao.getPatients()
}