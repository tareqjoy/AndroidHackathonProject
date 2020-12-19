package com.tigerit.androidhackathonproject

import android.app.Application
import com.tigerit.androidhackathonproject.di.application.ApplicationComponent
import com.tigerit.androidhackathonproject.di.application.ApplicationModule
import com.tigerit.androidhackathonproject.di.application.DaggerApplicationComponent


class MyApp : Application()
{

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }



}