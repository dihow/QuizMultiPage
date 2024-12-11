package com.example.quizmultipage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizmultipage.ui.theme.QuizMultiPageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizMultiPageTheme {
                Start()
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Preview(showSystemUi = true)
@Composable
fun Start(context: Context = LocalContext.current) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Географическая викторина (10 вопросов)", fontSize = 20.sp,
            modifier = Modifier.padding(30.dp))
        Button(onClick = {
            launcher.launch(Intent(context, QuizActivity::class.java)) },
            Modifier.padding(40.dp).shadow(20.dp, RoundedCornerShape(40.dp))) {
            Text(text = "Начать", fontSize = 30.sp)
        }
        Button(onClick = {
            launcher.launch(Intent(context, InstructionActivity::class.java)) },
            Modifier.padding(40.dp).shadow(20.dp, RoundedCornerShape(40.dp))) {
            Text(text = "Инструкция", fontSize = 30.sp)
        }
    }
}

data class Question(val text: String, val answers: List<String>, val correctAnswer: String,
                    var colors: MutableList<Color> = MutableList(answers.size) {Color.White},
                    var showAnswer: Boolean = false) {
    fun getCorrectAnswerId(): Int {
        return answers.indexOf(correctAnswer)
    }
}