package com.example

import android.app.Application
import com.example.api.ApiGateway
import com.example.api.HttpApiGateway
import com.example.login.LoginActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

open class ExampleApplication : Application() {

    companion object {
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    internal open var applicationModule: ApplicationModule = ApplicationModule(this)

    override fun onCreate() {
        super.onCreate()

        onInit()
    }

    open fun onInit() {
        graph = DaggerApplicationComponent
                .builder()
                .applicationModule(applicationModule)
                .build()

        graph.inject(this)
    }

}

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: ExampleApplication)
    fun inject(loginActivity: LoginActivity)
}

@Module
open class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    open fun provideApiGateway(): ApiGateway = HttpApiGateway()

}
