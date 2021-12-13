package com.example.testtaskforbootcamp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testtaskforbootcamp.App
import com.example.testtaskforbootcamp.R
import com.example.testtaskforbootcamp.di.ApplicationComponent


class MainActivity : AppCompatActivity() {

    private val component by lazy {
        ( application as App).component }

    override fun onCreate(savedInstanceState: Bundle?) {
component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }




    }
}