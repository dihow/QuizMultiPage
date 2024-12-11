package com.example.quizmultipage

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizmultipage.ui.theme.QuizMultiPageTheme

class EndActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizMultiPageTheme {
                val correctAnswers = intent.extras?.getInt("correctAnswers") ?: 0
                val totalAnswers = intent.extras?.getInt("totalAnswers") ?: 0
                End(correctAnswers, totalAnswers, this)
            }
        }
    }
}

@Composable
fun End(correctAnswers: Int = 2, totalAnswers: Int = 2, activity: Activity) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Правильные ответы: $correctAnswers из $totalAnswers",
            fontSize = 20.sp, modifier = Modifier.padding(0.dp, 100.dp, 0.dp, 50.dp))
        Button(onClick = {
            activity.finishAffinity()
        }, modifier = Modifier.shadow(10.dp, RoundedCornerShape(30.dp))) {
            Text(text = "Выход", fontSize = 25.sp)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun EndPreview(correctAnswers: Int = 2, totalAnswers: Int = 2) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Правильные ответы: $correctAnswers из $totalAnswers",
            fontSize = 20.sp, modifier = Modifier.padding(0.dp, 100.dp, 0.dp, 50.dp))
        Button(onClick = {
        }, modifier = Modifier.shadow(10.dp, RoundedCornerShape(30.dp))) {
            Text(text = "Выход", fontSize = 25.sp)
        }
    }
}