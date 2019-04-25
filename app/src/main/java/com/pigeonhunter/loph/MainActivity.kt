package com.pigeonhunter.loph

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startbt)
        startButton.setOnClickListener {
            val newIntent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(newIntent)
        }
    }
}
