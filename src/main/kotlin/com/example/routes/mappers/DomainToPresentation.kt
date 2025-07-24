package com.example.routes.mappers

import com.example.domain.models.Patient
import com.example.domain.models.Appointment
import com.example.domain.models.Doctor
import com.example.domain.models.MedicalProcedure
import com.example.domain.models.MedicalService
import com.example.routes.models.RegisterPatientRequest
import com.example.routes.models.AppointmentResponse
import com.example.routes.models.BookAppointmentRequest
import com.example.routes.models.DoctorResponse
import com.example.routes.models.ProcedureResponse
import com.example.routes.models.ServiceResponse
import java.time.LocalDate
import java.time.LocalDateTime

fun RegisterPatientRequest.toDomain(): Patient = Patient(
    id = null,
    userId = null,
    firstName = this.firstName,
    lastName = this.lastName,
    secondLastName = this.secondLastName,
    birthDate = this.birthDate?.let { LocalDate.parse(it) },
    gender = this.gender,
    address = this.address,
    cityId = this.cityId,
    phone = this.phone,
    mobilePhone = this.mobilePhone,
    emergencyContactName = this.emergencyContactName,
    emergencyContactPhone = this.emergencyContactPhone,
    nationalId = this.nationalId,
    socialSecurityNumber = this.socialSecurityNumber,
    createdAt = LocalDateTime.now().toString()
)

fun Appointment.toResponse(): AppointmentResponse = AppointmentResponse(
    id = this.id,
    patientId = this.patientId,
    doctorId = this.doctorId,
    serviceId = this.serviceId,
    procedureId = this.procedureId,
    appointmentDatetime = this.appointmentDatetime.toString(),
    durationMinutes = this.durationMinutes,
    reason = this.reason,
    status = this.status,
    createdAt = this.createdAt.toString()
)

fun List<Appointment>.toResponseList(): List<AppointmentResponse> = this.map { it.toResponse() }

// Mapper de presentación a dominio para BookAppointment
fun BookAppointmentRequest.toDomain(): Appointment = Appointment(
    id = "", // Se generará en el caso de uso
    patientId = "", // Se asignará en el caso de uso
    doctorId = this.doctorId,
    serviceId = this.serviceId,
    procedureId = this.procedureId,
    appointmentDatetime = LocalDateTime.parse(this.appointmentDatetime),
    durationMinutes = this.durationMinutes,
    reason = this.reason,
    status = "pending", // Se asignará en el caso de uso
    createdAt = LocalDateTime.now() // Se actualizará en el caso de uso
)

fun Doctor.toResponse(): DoctorResponse = DoctorResponse(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    secondLastName = this.secondLastName,
    licenseNumber = this.licenseNumber,
    collegeName = this.collegeName,
    email = this.email,
    phone = this.phone,
    mobilePhone = this.mobilePhone,
    specializationLevel = this.specializationLevel,
    yearsExperience = this.yearsExperience,
    medicalCenterId = this.medicalCenterId,
    isActive = this.isActive
)

// Mappers para el flujo de selección de citas
fun MedicalService.toResponse(): ServiceResponse = ServiceResponse(
    id = this.id,
    name = this.name,
    description = this.description
)

fun MedicalProcedure.toResponse(): ProcedureResponse = ProcedureResponse(
    id = this.id,
    name = this.name,
    description = this.description
)

fun List<MedicalService>.toServiceResponseList(): List<ServiceResponse> = this.map { it.toResponse() }
fun List<MedicalProcedure>.toProcedureResponseList(): List<ProcedureResponse> = this.map { it.toResponse() }
fun List<Doctor>.toDoctorResponseList(): List<DoctorResponse> = this.map { it.toResponse() }

// Mappers de dominio a presentación para las nuevas funcionalidades
fun MedicalService.toPresentationMedicalService(): ServiceResponse = ServiceResponse(
    id = this.id,
    name = this.name,
    description = this.description
)

fun MedicalProcedure.toPresentationMedicalProcedure(): ProcedureResponse = ProcedureResponse(
    id = this.id,
    name = this.name,
    description = this.description
)

fun Doctor.toPresentationDoctor(): DoctorResponse = DoctorResponse(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    secondLastName = this.secondLastName,
    licenseNumber = this.licenseNumber,
    collegeName = this.collegeName,
    email = this.email,
    phone = this.phone,
    mobilePhone = this.mobilePhone,
    specializationLevel = this.specializationLevel,
    yearsExperience = this.yearsExperience,
    medicalCenterId = this.medicalCenterId,
    isActive = this.isActive
)
