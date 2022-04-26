package learning.kotlin.part2.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main) // R.layout.액티비티종류 는 그 액티비티의 주소를 뜻한다. 여기서는 1300000

        val heightEditText : EditText = findViewById(R.id.heightEditText)
        val weightEditText = findViewById<EditText>(R.id.weightEditText)

        val resultButton = findViewById<Button>(R.id.resultButton)

        resultButton.setOnClickListener{
            // Log.d("Mainactivity","ResultButton이 클릭되었습니다.")

            if (heightEditText.text.isEmpty() || weightEditText.text.isEmpty()){
                Toast.makeText(this,"빈 값이 있습니다.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 이 아래로는 절대 빈 값이 올 수 없음.

            val height:Int = heightEditText.text.toString().toInt()
            val weight:Int = weightEditText.text.toString().toInt()

            //Log.d("MainActivity","height : $height weight $weight")

            val intent = Intent(this,ResultActivity::class.java) // Intent(현재 액티비티, 넘어갈 액티비티::class.java)

            intent.putExtra("height",height) // int를 넘길 건데, "height"라는 이름으로 넘기겠다 는 뜻
            intent.putExtra("weight",weight)

            startActivity(intent)
        }

    }
}