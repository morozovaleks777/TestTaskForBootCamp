package com.example.testtaskforbootcamp

import android.app.Application
import com.example.testtaskforbootcamp.di.DaggerApplicationComponent


class App:Application() {


  val component by lazy { DaggerApplicationComponent
        .factory()
        .create(this ) }
}