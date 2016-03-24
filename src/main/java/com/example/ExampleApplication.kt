package com.example

import android.app.Activity
import android.app.Application
import com.example.api.ApiGateway
import com.example.api.HttpApiGateway
import com.example.login.LoginActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

class ExampleApplication : Application() {

    companion object {
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        graph = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
        graph.inject(this)
    }
}

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {

    fun inject(application: ExampleApplication)
    fun inject(loginActivity: LoginActivity)
}

@Module
class AndroidModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApiGateway(): ApiGateway = HttpApiGateway()

}
