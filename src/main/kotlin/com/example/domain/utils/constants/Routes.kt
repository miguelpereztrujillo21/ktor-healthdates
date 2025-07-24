package com.example.domain.utils.constants

object Routes {
    const val REGISTER = "/register"
    //PATIENT
    const val PATIENTREGISTER = "/register-patient"
    const val PATIENTLOGIN = "/login-patient"
    const val PATIENT_APPOINTMENTS = "/patient-appointments"
    const val BOOK_APPOINTMENT = "/book-appointment"

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