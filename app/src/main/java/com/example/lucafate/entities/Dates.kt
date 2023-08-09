package com.example.lucafate.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dates")
class Dates (
    @PrimaryKey(autoGenerate = true)
    var id: Int
)