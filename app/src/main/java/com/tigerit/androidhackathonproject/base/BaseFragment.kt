package com.tigerit.androidhackathonproject.base

import androidx.fragment.app.Fragment
import com.tigerit.androidhackathonproject.MyApp
import com.tigerit.androidhackathonproject.di.activity.ActivityComponent
import com.tigerit.androidhackathonproject.di.activity.modules.ActivityModule
import com.tigerit.androidhackathonproject.di.application.ApplicationComponent

open class BaseFragment : Fragment() {
    val appComponent: ApplicationComponent by lazy {
        (requireActivity().application as MyApp).appComponent
    }

    val activityComponent: ActivityComponent by lazy {
        appComponent.getActivityComponent(ActivityModule(requireActivity()))
    }

}