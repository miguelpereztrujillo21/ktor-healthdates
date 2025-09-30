package com.example.domain.utils.constants

object Routes {
    // Authentication routes
    const val REGISTER = "/register"
    const val LOGIN = "/login"

    // Patient routes
    const val PATIENTREGISTER = "/register-patient"
    const val PATIENTLOGIN = "/login-patient"
    const val PATIENT_APPOINTMENTS = "/patient-appointments"
    const val BOOK_APPOINTMENT = "/patient-appointments"
    const val PATIENT_PROFILE = "/patient-profile"

    // SELECTION ROUTES
    const val SELECTION = "/selection"

    // SELECTION SUB-ROUTES
    const val SERVICES = "/services"
    const val PROCEDURES = "/procedures"
    const val DOCTORS = "/doctors"
    const val AVAILABLE = "/available"
    const val SERVICES_BY_ID = "/services/{serviceId}"
    const val SERVICES_BY_ID_PROCEDURES = "/services/{serviceId}/procedures"
    const val SERVICES_BY_ID_DOCTORS = "/services/{serviceId}/doctors"
    const val DOCTORS_AVAILABLE = "/doctors/available"
}