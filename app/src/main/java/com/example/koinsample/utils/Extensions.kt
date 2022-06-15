package com.example.koinsample.utils

import org.joda.time.DateTime

fun Int.fromApiTime(): DateTime {
    return DateTime(this * 1000L)
}