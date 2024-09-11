package com.example.surakshitaandroid

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var tvGeneratedPassword: TextView
    private lateinit var tvLength: TextView
    private lateinit var seekBarLength: SeekBar
    private lateinit var cbIncludeLetters: CheckBox
    private lateinit var cbIncludeNumbers: CheckBox
    private lateinit var cbIncludeSpecial: CheckBox
    private lateinit var btnGeneratePassword: Button

    private var passwordLength: Int = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        tvGeneratedPassword = findViewById(R.id.tvGeneratedPassword)
        tvLength = findViewById(R.id.tvLength)
        seekBarLength = findViewById(R.id.seekBarLength)
        cbIncludeLetters = findViewById(R.id.cbIncludeLetters)
        cbIncludeNumbers = findViewById(R.id.cbIncludeNumbers)
        cbIncludeSpecial = findViewById(R.id.cbIncludeSpecial)
        btnGeneratePassword = findViewById(R.id.btnGeneratePassword)

        // Update password length as the SeekBar moves
        seekBarLength.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                passwordLength = progress
                tvLength.text = "Password Length: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Generate password on button click
        btnGeneratePassword.setOnClickListener {
            val password = generatePassword(
                passwordLength,
                cbIncludeLetters.isChecked,
                cbIncludeNumbers.isChecked,
                cbIncludeSpecial.isChecked
            )
            tvGeneratedPassword.text = password
        }
    }

    // Function to generate random password based on user's choices
    private fun generatePassword(length: Int, includeLetters: Boolean, includeNumbers: Boolean, includeSpecial: Boolean): String {
        val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        val numbers = "0123456789"
        val special = "!@#\$%^&*()-_=+[]{}|;:'\",.<>?/`~"

        var charPool = ""

        // Add character sets based on user's selection
        if (includeLetters) charPool += letters
        if (includeNumbers) charPool += numbers
        if (includeSpecial) charPool += special

        if (charPool.isEmpty()) return "Please select at least one option"

        // Generate password
        return (1..length)
            .map { Random.nextInt(0, charPool.length) }
            .map(charPool::get)
            .joinToString("")
    }
}