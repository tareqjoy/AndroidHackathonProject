package com.tigerit.androidhackathonproject.di.activity.modules

import android.app.Activity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.tigerit.androidhackathonproject.ui.helpers.ActivityNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideFragmentActivity(): FragmentActivity {
        return activity as FragmentActivity
    }

    @Provides
    fun provideFragmentManager(fragmentActivity: FragmentActivity): FragmentManager {
        return fragmentActivity.supportFragmentManager
    }


    @Provides
    fun provideActivityNavigator(activity: Activity): ActivityNavigator {
        return ActivityNavigator(activity)
    }




}