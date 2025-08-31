package com.example.calculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import com.example.calculator.databinding.ActivityMainBinding
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val expression = binding.expression
        val result = binding.result

        expression.movementMethod = ScrollingMovementMethod()

        var str: String

        binding.clear.setOnClickListener {
            expression.setText(" ")
            result.setText(" ")
        }
        binding.backspace.setOnClickListener {
            if (result.text.isNotEmpty()) {
                str = result.text.substring(0, result.text.length - 1)
                result.setText(str)
            }
        }

        binding.add.setOnClickListener { appendToResult("+") }
        binding.subtract.setOnClickListener { appendToResult("-") }
        binding.multiply.setOnClickListener { appendToResult("*") }
        binding.divide.setOnClickListener { appendToResult("/") }
        binding.percent.setOnClickListener { appendToResult("%") }
        binding.dot.setOnClickListener { appendToResult(".") }

        binding.equal.setOnClickListener {
            val exp = result.text.toString()
            if (exp.isNotEmpty()) {
                val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
                try {
                    val res = engine.eval(exp).toString()
                    val finalResult = if (res.endsWith(".0")) {
                        res.replace(".0", "")
                    } else res
                    expression.textSize = 20f
                    val lighterColor = ColorUtils.setAlphaComponent(getColor(R.color.brown), 125)
                    expression.setTextColor(lighterColor)
                    expression.setText(exp)
                    result.setText(finalResult)
                } catch (e: Exception) {
                    result.setText(getString(R.string.error))
                }
            }
        }

        binding.zero.setOnClickListener { appendToResult("0") }
        binding.zeroo.setOnClickListener { appendToResult("00") }
        binding.one.setOnClickListener { appendToResult("1") }
        binding.two.setOnClickListener { appendToResult("2") }
        binding.three.setOnClickListener { appendToResult("3") }
        binding.four.setOnClickListener { appendToResult("4") }
        binding.five.setOnClickListener { appendToResult("5") }
        binding.six.setOnClickListener { appendToResult("6") }
        binding.seven.setOnClickListener { appendToResult("7") }
        binding.eight.setOnClickListener { appendToResult("8") }
        binding.nine.setOnClickListener { appendToResult("9") }

    }
    private fun appendToResult(str: String) {
        if (binding.result.text.toString() == "0") {
            binding.result.setText(str)
        } else {
            binding.result.append(str)
        }
    }
}