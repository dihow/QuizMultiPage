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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizmultipage.ui.theme.QuizMultiPageTheme

class QuizActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizMultiPageTheme {
                Program()
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Preview(showSystemUi = true)
@Composable
fun Program(context: Context = LocalContext.current) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }

    val questions = remember {
        mutableListOf(
            Question(
                "1. Столица России?",
                listOf("Москва", "Санкт-Петербург", "Новосибирск"),
                "Москва"
            ),
            Question(
                "2. Самый высокий пик в мире?",
                listOf("Эверест", "К2", "Эльбрус"),
                "Эверест"
            ),
            Question(
                "3. Самый крупный континент?",
                listOf("Африка", "Южная Америка", "Евразия"),
                "Евразия"
            ),
            Question(
                "4. Самый высокий водопад в мире?",
                listOf("Ниагара", "Анхель", "Игуасу"),
                "Анхель"
            ),
            Question(
                "5. Самое глубокое озеро в мире?",
                listOf("Каспийское море", "Танганьика", "Байкал"),
                "Байкал"
            ),
            Question(
                "6. Самая большая страна в мире?",
                listOf("Россия", "США", "Канада", "Китай"),
                "Россия"
            ),
            Question(
                "7. Самая маленькая страна в мире?",
                listOf("Сан-Марино", "Монако", "Ватикан"),
                "Ватикан"
            ),
            Question(
                "8. Крупнейший остров в Средиземном море?",
                listOf("Сицилия", "Кипр", "Сардиния"),
                "Сицилия"
            ),
            Question(
                "9. Какое из течений Северного Ледовитого океана является тёплым?",
                listOf("Восточно-Гренландское", "Восточно-Исландское", "Норвежское"),
                "Норвежское"
            ),
            Question(
                "10. Самый жаркий континент?",
                listOf("Австралия", "Африка", "Южная Америка"),
                "Африка"
            ),
        )
    }
    val questionId = remember { mutableStateOf(0) }
    val correctAnswers = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Географическая викторина", fontSize = 20.sp,
            modifier = Modifier.padding(30.dp)
        )
        Text(
            text = questions[questionId.value].text,
            fontSize = 17.sp, modifier = Modifier.padding(30.dp, 10.dp, 0.dp, 20.dp)
        )
        questions[questionId.value].answers.forEachIndexed { index, option ->
            Button(
                onClick = {
                    if (!questions[questionId.value].showAnswer) {
                        if (option == questions[questionId.value].correctAnswer) {
                            questions[questionId.value].colors[index] = Color.Green
                            correctAnswers.value++
                        } else {
                            questions[questionId.value].colors[index] = Color.Red
                            questions[questionId.value].colors[questions[questionId.value].getCorrectAnswerId()] =
                                Color.Green
                        }
                        questionId.value++
                        questionId.value--
                        questions[questionId.value].showAnswer = true
                    }
                },
                modifier = Modifier.padding(30.dp, 5.dp)
                    .shadow(10.dp, RoundedCornerShape(30.dp))
            ) {
                Text(text = option, color = questions[questionId.value].colors[index])
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(30.dp, 40.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround
        )
        {
            if (questionId.value > 0)
                Button(onClick = {
                    questionId.value -= 1
                }) {
                    Text(text = "Назад")
                }
            if (questionId.value < questions.size - 1)
                Button(onClick = {
                    questionId.value += 1
                }, modifier = Modifier.shadow(10.dp, RoundedCornerShape(30.dp))) {
                    Text(text = "Далее")
                }
            else {
                Button(onClick = {
                    val intent = Intent(context, EndActivity::class.java)
                    intent.putExtra("correctAnswers", correctAnswers.value)
                    intent.putExtra("totalAnswers", questions.size)
                    launcher.launch(intent)
                }, modifier = Modifier.shadow(10.dp, RoundedCornerShape(30.dp))) {
                    Text(text = "Завершить")
                }
            }
        }
    }
}