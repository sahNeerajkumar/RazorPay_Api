package com.example.api2

data class PostDataModel(
    val contact: String,
    val name: String,
    val email: String,
    val fail_existing: String,
    val gstin: String,
    val notes: Notes
)