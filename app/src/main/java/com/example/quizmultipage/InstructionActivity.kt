package com.example.quizmultipage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizmultipage.ui.theme.QuizMultiPageTheme

class InstructionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizMultiPageTheme {
                Instruction()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Instruction(context: Context = LocalContext.current) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }
    Column (
        Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
        Text("Инструкция", fontSize = 28.sp)
        Text("Для начала тестирования нажмите на кнопку \"Начать\".\n" +
                "Во время тестирования выберите вариант ответа на поставленный вопрос.\n" +
                "Если затрудняетесь, можете пропустить вопрос, чтобы вернуться к нему позже.\n" +
                "После ответа на последний вопрос нажмите на кнопку \"Завершить\", " +
                "чтобы перейти к результатам.\nДля выхода из приложения нажмите на кнопку " +
                "\"Завершить\".", fontSize = 20.sp)
        Button(
            onClick = {
                launcher.launch(Intent(context, MainActivity::class.java))
            }
        ) { Text("Назад", fontSize = 28.sp) }
    }
}