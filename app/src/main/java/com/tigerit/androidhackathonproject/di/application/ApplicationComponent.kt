package com.tigerit.androidhackathonproject.di.application

import com.tigerit.androidhackathonproject.di.ViewModelModule
import com.tigerit.androidhackathonproject.di.activity.ActivityComponent
import com.tigerit.androidhackathonproject.di.activity.modules.ActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent
{
    fun getActivityComponent(activityModule: ActivityModule): ActivityComponent
}