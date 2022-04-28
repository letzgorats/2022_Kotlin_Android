package learning.kotlin.part2.lotterynumberdraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {


    private val addButton : Button by lazy{
        findViewById<Button>(R.id.addButton)
    }

    private val clearButton : Button by lazy{
        findViewById<Button>(R.id.clearButton)
    }

    private val randomButton : Button by lazy{
        findViewById<Button>(R.id.randomButton)
    }

    private val numberPicker : NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private val numberTextViewList : List<TextView> by lazy {
        listOf<TextView>(
            findViewById<TextView>(R.id.textView1),
            findViewById<TextView>(R.id.textView2),
            findViewById<TextView>(R.id.textView3),
            findViewById<TextView>(R.id.textView4),
            findViewById<TextView>(R.id.textView5),
            findViewById<TextView>(R.id.textView_bonus)
        )

    }

    private var didRun = false // 이미 랜덤 버튼 눌렀을 경우를 대비하기 위한 boolean 변수

    private val pickNumberSet = mutableSetOf<Int>() // hashSetOf<Int> 도 가능


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()
    }

    private fun initAddButton(){
        addButton.setOnClickListener {
            if (didRun) {   // 이미 자동생성으로 번호가 다 뽑혔을 경우를 대비하는 예외처리
                Toast.makeText(this, "초기화 후에 시도해주세요 ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pickNumberSet.size >= 6){    // 6개 까지만 뽑혀야 하는 예외처리
                Toast.makeText(this, "번호는 6개까지만 선택하실 수 있습니다 ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pickNumberSet.contains(numberPicker.value)){  // numberPicker 에서 선택한 숫자가 pickNumberSet 에 이미 포함되어 있는 것을 대비하는 예외처리
                Toast.makeText(this, "이미 선택한 번호입니다 ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]  // pickNumberSet의 현재 size가 이제 들어갈 인덱스라고 할 수 있다. (앞에서 중복처리 해줬기 때문에)
            textView.isVisible = true
            textView.text = numberPicker.value.toString()

            // 범위에 따른 공색깔 설정
            setNumberBackground(numberPicker.value,textView)

            pickNumberSet.add(numberPicker.value)

        }
    }

    private fun initClearButton(){
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTextViewList.forEach{
                it.isVisible = false
            }
            didRun = false // didRun 값도 초기화
        }
    }


    private fun initRunButton(){
        randomButton.setOnClickListener {
            val list = getRandomNumber()

            didRun = true

            list.forEachIndexed{ index, number ->
                val textView = numberTextViewList[index]

                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number,textView)
            }

            Log.d("MainActivity",list.toString())
        }
    }

    private fun setNumberBackground(number:Int, textView: TextView){
        when(number){
            in 1..7 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_yellow)
            in 8..14 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_blue)
            in 15..22 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_red)
            in 23..30 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_gray)
            in 31..38 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_green)
            else -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_black)
        }
    }

    private fun getRandomNumber():List<Int>{

        val numberList = mutableListOf<Int>()
            .apply{
                for(i in 1..45){
                    if (pickNumberSet.contains(i)){continue}
                    this.add(i)
                }
            }
        numberList.shuffle()

        val newList = pickNumberSet.toList() + numberList.subList(0,6-pickNumberSet.size)

        return newList.sorted()
    }


}
