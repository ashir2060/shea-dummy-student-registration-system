package com.example.myapp.ui.theme

import androidx.room.Entity //Entity
import androidx.room.PrimaryKey //PrimaryKey

@Entity(tableName = "students")
data class StudentEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentName: String,
    val studentId: String
)