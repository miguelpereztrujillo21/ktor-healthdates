package com.example.data.mappers

import com.example.data.tables.*
import com.example.domain.models.*
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toDomainAppointment(): Appointment = Appointment(
    id = this[Appointments.id].toString(),
    patientId = this[Appointments.patientId].toString(),
    doctorId = this[Appointments.doctorId].toString(),
    serviceId = this[Appointments.serviceId],
    procedureId = this[Appointments.procedureId],
    appointmentDatetime = this[Appointments.appointmentDatetime],
    durationMinutes = this[Appointments.durationMinutes],
    reason = this[Appointments.reason],
    status = this[Appointments.status],
    createdAt = this[Appointments.createdAt]
)

// Mapper de data a dominio para Patient
fun ResultRow.toDomainPatient(): Patient = Patient(
    id = this[Patients.id].toString(),
    userId = this[Patients.userId]?.toString(),
    firstName = this[Patients.firstName],
    lastName = this[Patients.lastName],
    secondLastName = this[Patients.secondLastName],
    birthDate = this[Patients.birthDate],
    gender = this[Patients.gender],
    address = this[Patients.address],
    cityId = this[Patients.cityId],
    phone = this[Patients.phone],
    mobilePhone = this[Patients.mobilePhone],
    emergencyContactName = this[Patients.emergencyContactName],
    emergencyContactPhone = this[Patients.emergencyContactPhone],
    nationalId = this[Patients.nationalId],
    socialSecurityNumber = this[Patients.socialSecurityNumber],
    createdAt = this[Patients.createdAt].toString()
)


fun ResultRow.toDomainDoctor(): Doctor = Doctor(
    id = this[Doctors.id].toString(),
    userId = this[Doctors.userId].toString(), // Removido el safe call ya que no es nullable
    medicalCenterId = this[Doctors.medicalCenterId]?.toString(),
    firstName = this[Doctors.firstName],
    lastName = this[Doctors.lastName],
    secondLastName = this[Doctors.secondLastName],
    licenseNumber = this[Doctors.licenseNumber],
    collegeName = this[Doctors.collegeName],
    email = this[Doctors.email],
    phone = this[Doctors.phone],
    mobilePhone = this[Doctors.mobilePhone],
    specializationLevel = this[Doctors.specializationLevel],
    yearsExperience = this[Doctors.yearsExperience],
    isActive = this[Doctors.isActive],
    createdAt = this[Doctors.createdAt].toString()
)

// Mappers para servicios médicos
fun ResultRow.toDomainMedicalService(): MedicalService = MedicalService(
    id = this[MedicalServices.id],
    name = this[MedicalServices.name],
    description = this[MedicalServices.description]
)

fun ResultRow.toDomainMedicalProcedure(): MedicalProcedure = MedicalProcedure(
    id = this[MedicalProcedures.id],
    name = this[MedicalProcedures.name],
    description = this[MedicalProcedures.description]
)
