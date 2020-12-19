package com.tigerit.androidhackathonproject.base

import androidx.appcompat.app.AppCompatActivity
import com.tigerit.androidhackathonproject.MyApp
import com.tigerit.androidhackathonproject.di.activity.ActivityComponent
import com.tigerit.androidhackathonproject.di.activity.modules.ActivityModule
import com.tigerit.androidhackathonproject.di.application.ApplicationComponent


open class BaseActivity : AppCompatActivity()
{
    val appComponent: ApplicationComponent by lazy {
        (application as MyApp).appComponent
    }

    val activityComponent: ActivityComponent by lazy {
        appComponent.getActivityComponent(ActivityModule(this))
    }

}