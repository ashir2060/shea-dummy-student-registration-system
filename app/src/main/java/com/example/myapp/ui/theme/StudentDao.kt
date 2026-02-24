package com.example.myapp.ui.theme

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {

    // Insert one student
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    // Get all students
    @Query("SELECT * FROM students ORDER BY id DESC")
    suspend fun getAllStudents(): List<StudentEntity>

    // Delete all students
    @Query("DELETE FROM students")
    suspend fun deleteAllStudents()

    // Delete one student by id
    @Query("DELETE FROM students WHERE id = :studentId")
    suspend fun deleteStudentById(studentId: Int)
}