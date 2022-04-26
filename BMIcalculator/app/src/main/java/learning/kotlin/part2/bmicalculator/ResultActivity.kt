package learning.kotlin.part2.bmicalculator

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.round

class ResultActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // 넘어온 intent에 "height" 이라는 값이 없다면, 기본값을 0 으로 가진다는 뜻
        val height = intent.getIntExtra("height",0)
        val weight = intent.getIntExtra("weight",0)

        Log.d("ResultActivity","height : $height, weight : $weight")

        val bmi = round((weight / (height / 100.0).pow(2.0)) * 100) / 100.0

        // when 에서 나오는 결과값이 표현식을 통해 변수에 할당된다.
        val resultText = when{
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중정도 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상 체중"
            else -> "저체중"
        }

        val resultCalculatedTextView = findViewById<TextView>(R.id.bmiCalculatedTextView)
        val resultStringTextView = findViewById<TextView>(R.id.resultTextView)

        resultCalculatedTextView.text = bmi.toString()
        resultStringTextView.text = resultText

    }
}