package com.example.domain.utils.constants

object RegexConstants {
    val EMAIL_REGEX: Regex = Regex(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
        RegexOption.IGNORE_CASE
    )
}