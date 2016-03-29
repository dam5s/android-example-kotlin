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
        @JvmStatic lateinit var container: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        initializeContainer(ApplicationModule(this))
    }

    open fun initializeContainer(m : ApplicationModule) {
        container = DaggerApplicationComponent
                .builder()
                .applicationModule(m)
                .build()
    }

}

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(loginActivity: LoginActivity)
}

@Module
open class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    open fun provideApiGateway(): ApiGateway = HttpApiGateway()

}
