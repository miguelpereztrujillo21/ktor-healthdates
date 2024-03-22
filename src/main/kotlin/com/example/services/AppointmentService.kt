package com.example.services

import com.example.models.Appointment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection

class AppointmentService (private val connection: Connection){
    companion object{
        private const val SELECT_ALL_APPOINTMENT_BY_USER_ID =
            """
    SELECT  a.appointment_id, a.appointment_date,a.appointment_status,d.doctor_name AS doctor_name,s.service_name AS service_name,t.treatment_name AS treatment_name,c.center_name AS center_name,u.user_name AS user_name
    FROM appointments a
    JOIN doctors d ON a.doctor_id = d.doctor_id
    JOIN services s ON a.service_id = s.service_id
    JOIN treatments t ON a.treatment_id = t.treatment_id
    JOIN centers c ON a.center_id = c.center_id
    JOIN users u ON a.user_id = u.user_id WHERE  a.user_id = :userId"""
    }

    suspend fun getAppointmentById(id: String): ArrayList<Appointment> = withContext(Dispatchers.IO) {
        val statement = connection.prepareStatement(SELECT_ALL_APPOINTMENT_BY_USER_ID.replace(":userId", "'$id'"))
        val resultSet = statement.executeQuery()
        val appointmetsList = ArrayList<Appointment>()
         while (resultSet.next()) {
          val appointment =   Appointment(
                resultSet.getInt("appointment_id"),
                resultSet.getString("appointment_date"),
                resultSet.getString("appointment_status"),
                resultSet.getString("doctor_name"),
                resultSet.getString("service_name"),
                resultSet.getString("treatment_name"),
                resultSet.getString("center_name"),
                resultSet.getString("user_name")
            )
             appointmetsList.add(appointment)
        }
        if (appointmetsList.isEmpty()) {
            throw Exception("No results found for appointment with id $id")
        }
        return@withContext appointmetsList
    }
}