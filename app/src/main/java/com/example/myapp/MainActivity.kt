package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapp.ui.theme.MyAppTheme
//import for TextField
import androidx.compose.material3.TextField
//import for dp
import androidx.compose.ui.unit.dp
//import for offset
import androidx.compose.foundation.layout.offset
//import for width & height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
//import for column
import androidx.compose.foundation.layout.Column
//import for spacer
import androidx.compose.foundation.layout.Spacer
//import mutable state
import androidx.compose.runtime.mutableStateOf
//import remember
import androidx.compose.runtime.remember
//import getValue
import androidx.compose.runtime.getValue
//import setValue
import androidx.compose.runtime.setValue
//import material3.Button runtime.rememberCoroutineScope ui.platform.LocalContext launch
import androidx.compose.material3.Button
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

//import AppDatabase StudentEntity
import com.example.myapp.ui.theme.AppDatabase
import com.example.myapp.ui.theme.StudentEntity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                val context = LocalContext.current
                val db = remember { AppDatabase.getDatabase(context) }
                val scope = rememberCoroutineScope() //scope
                val dao = remember {db.studentDao()} //dao

                var studentName by remember { mutableStateOf("") }
                var studentId by remember {mutableStateOf("") }
                var status by remember { mutableStateOf("") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Student_Name_Ask(
                            value = studentName,
                            onValueChange = { studentName = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Student_ID_Ask(
                            value = studentId,
                            onValueChange = { studentId = it}
                        )
                    }
                    Button(onClick = {
                        scope.launch {
                            if(studentName.isBlank() || studentId.isBlank()) {
                                status = "Please enter both fields"
                            } else {
                                dao.insertStudent(
                                    StudentEntity( studentName = studentName, studentId = studentId )
                                )
                                status = "Saved"
                                studentName = ""
                                studentId = ""
                            }
                        }
                    }) {
                        Text("Save")
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(status)
                }
            }
        }
    }
}

@Composable
fun Student_Name_Ask(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Student Name",
        modifier = modifier
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text("Enter Student Name")
        },
        modifier = Modifier
            .offset(x = 10.dp, y = 0.dp)
            .width(200.dp)
            .height(50.dp)
    )
}

@Composable
fun Student_ID_Ask(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Text(
        text = "Student ID",
        modifier = modifier
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text("Enter Student ID")
        },
        modifier = Modifier
            .offset(x = 10.dp, y = 0.dp)
            .width(200.dp)
            .height(50.dp)
    )
}