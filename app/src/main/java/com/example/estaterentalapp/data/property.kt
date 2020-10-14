package com.example.estaterentalapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class property (
    @PrimaryKey
    val id: String,
    val property_title: String,
    val image_URL: String,
    val estate: String,
    val bedrooms:	String,
    val gross_area:	String,
    val expected_tenants:	String,
    val rent:	String,
    val h_Propert: String,
    var occupied: Boolean = false
) { } //the curly braces can be omitted.