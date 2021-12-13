package com.example.testtaskforbootcamp.di


import android.content.Context
import com.example.testtaskforbootcamp.presentation.MainActivity
import com.example.testtaskforbootcamp.presentation.MainFragment
import com.example.testtaskforbootcamp.presentation.ViewModelFactory
import com.example.testtaskforbootcamp.presentation.ViewModelFactory_Factory
import dagger.Binds
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class,DomainModule::class,ViewModelModule::class])
interface ApplicationComponent {

   fun inject(activity: MainActivity)
  //fun inject(mainFragment: MainFragment)



    @Component.Factory
    interface ApplicationComponentFactory{

        fun create(
            @BindsInstance context: Context,
          // @BindsInstance applicationContext: Context

        ): ApplicationComponent
    }
}