package com.example.questionandanswer

import android.R
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.questionandanswer.databinding.ActivityMathBinding
import kotlin.random.Random


class Math_Activity : AppCompatActivity() {
    private val binding by lazy { ActivityMathBinding.inflate(layoutInflater)}
    private var number1=0
    private var number2=0
    var summa=0
    var count=0
    var count_true=0
    var count_false=0
    private var radioButton:RadioButton?=null
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        hammasi()
        // checked()
        binding.apply {
            txtNext.setOnClickListener{
                binding.radiogroup.setOnCheckedChangeListener { _, id ->
                    radioButton = findViewById(id)
                    if (radioButton?.text == summa.toString()) {
                        count_true++
                    } else {
                        count_false++
                    }
                }
                count++
                testCount.text=count.toString()
                hammasi()
                radiogroup.clearCheck()
                if(count==10){
                    val dialog = AlertDialog.Builder(this@Math_Activity)
                        .setTitle("Sizning natijangiz")
                        .setMessage("Siz 10 ta savoldan $count_true to'gri natija ko'rsatdingiz. $count_false ta xato javob berdingiz")
                        .setPositiveButton("Tugatish",object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val intent = Intent(this@Math_Activity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    })
                        .setNegativeButton("Boshidan",object :DialogInterface.OnClickListener{
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                val intent = Intent(this@Math_Activity, Math_Activity::class.java)
                                startActivity(intent)
                                count=0
                                count_true=0
                                count_false=0
                                binding.testCount.text="1"
                            }
                        })
                        .create()

                    dialog.show()
                }
            }

        }


    }


    fun amalBtn():Int{
        return Random.nextInt(1,5)
    }
    fun amalRandom():Int{
        val nums= Random.nextInt(1,3)
        var sum=0
        when(nums) {
            1 -> {
                binding.txtAmal.text = "+"
                sum =
                    binding.txtNumber1.text.toString().toInt() + binding.txtNumber2.text.toString()
                        .toInt()
            }
            2 -> {
                binding.txtAmal.text = "-"
                sum =
                    binding.txtNumber1.text.toString().toInt() - binding.txtNumber2.text.toString()
                        .toInt()
            }
        }
        return sum
    }
    fun numberRandom(){
        number1=Random.nextInt(1,20)
        number2=Random.nextInt(1,20)
        if (number1>number2){
            binding.txtNumber1.text=number1.toString()
            binding.txtNumber2.text=number2.toString()
        }else{
            return numberRandom()
        }
    }
    fun numberBtn():Int{
        return Random.nextInt(1,20)
    }
    fun randomNumberBtn(num1: Int):ArrayList<Int> {
        val num2 = numberBtn()
        val num3 = numberBtn()
        val num4 = numberBtn()
        val intArray = arrayListOf<Int>()
        intArray.add(num1)
        intArray.add(num2)
        intArray.add(num3)
        intArray.add(num4)
        if (num1 != num2 && num2 != num3 && num3 != num4 && num1!=num4) {
            return intArray
        }
        return randomNumberBtn(num1)
    }
    @SuppressLint("ResourceAsColor")
//    fun checked(){
//        if (binding.radio1.isChecked){
//            radioTekshir = 1
//            binding.cardView1.setCardBackgroundColor(R.color.defaults)
//            binding.cardView2.setCardBackgroundColor(R.color.white)
//            binding.cardView3.setCardBackgroundColor(R.color.white)
//            binding.cardView4.setCardBackgroundColor(R.color.white)
//            binding.radio4.isEnabled=false
//            binding.radio2.isEnabled=false
//            binding.radio3.isEnabled=false
//        }
//
//        if (binding.radio2.isChecked) {
//            radioTekshir = 2
//            binding.cardView2.setCardBackgroundColor(R.color.defaults)
//            binding.cardView1.setCardBackgroundColor(R.color.white)
//            binding.cardView3.setCardBackgroundColor(R.color.white)
//            binding.cardView4.setCardBackgroundColor(R.color.white)
//            binding.radio1.isEnabled=false
//            binding.radio4.isEnabled=false
//            binding.radio3.isEnabled=false
//        }
//
//        if (binding.radio3.isChecked) {
//            radioTekshir = 3
//            binding.cardView3.setCardBackgroundColor(R.color.defaults)
//            binding.cardView1.setCardBackgroundColor(R.color.white)
//            binding.cardView2.setCardBackgroundColor(R.color.white)
//            binding.cardView4.setCardBackgroundColor(R.color.white)
//            binding.radio1.isEnabled=false
//            binding.radio4.isEnabled=false
//            binding.radio2.isEnabled=false
//        }
//
//       if (binding.radio4.isChecked){
//            radioTekshir = 4
//            binding.cardView4.setCardBackgroundColor(R.color.defaults)
//            binding.cardView1.setCardBackgroundColor(R.color.white)
//            binding.cardView2.setCardBackgroundColor(R.color.white)
//            binding.cardView3.setCardBackgroundColor(R.color.white)
//           binding.radio1.isEnabled=false
//           binding.radio2.isEnabled=false
//           binding.radio3.isEnabled=false
//       }
//    }
    fun hammasi(){
        numberBtn()
        numberRandom()
        summa=amalRandom()
        val array:ArrayList<Int> = randomNumberBtn(summa)
        when (amalBtn()) {
            1 -> {
                binding.radio1.text = summa.toString()
                binding.radio2.text = array[1].toString()
                binding.radio3.text = array[2].toString()
                binding.radio4.text = array[3].toString()
            }
            2 -> {
                binding.radio2.text = summa.toString()
                binding.radio3.text = array[1].toString()
                binding.radio4.text = array[2].toString()
                binding.radio1.text = array[3].toString()
            }
            3 -> {
                binding.radio3.text = summa.toString()
                binding.radio1.text = array[1].toString()
                binding.radio2.text = array[2].toString()
                binding.radio4.text = array[3].toString()
            }
            4 -> {
                binding.radio4.text= summa.toString()
                binding.radio1.text = array[1].toString()
                binding.radio2.text = array[2].toString()
                binding.radio3.text = array[3].toString()
            }
        }
    }
}