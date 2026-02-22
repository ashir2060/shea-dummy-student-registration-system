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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Student_Name_Ask()

                        Spacer(modifier = Modifier.height(16.dp))

                        Student_ID_Ask() //i just wrote this to try commit
                    }
                }
            }
        }
    }
}

@Composable
fun Student_Name_Ask(modifier: Modifier = Modifier) {
    Text(
        text = "Student Name",
        modifier = modifier
    )
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText // android studio ide thinks text is never used because by default there's a variable-kind-of thing setup like value="" in the android studio
        },
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
fun Student_ID_Ask(modifier: Modifier = Modifier){
    Text(
        text = "Student ID",
        modifier = modifier
    )
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        placeholder = {
            Text("Enter student ID")
        },
        modifier = Modifier
                    .offset(x = 10.dp,y = 0.dp)
                    .width(200.dp)
                    .height(50.dp)
    )
}