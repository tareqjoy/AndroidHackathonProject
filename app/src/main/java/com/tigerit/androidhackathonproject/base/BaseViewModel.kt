package com.tigerit.androidhackathonproject.base

import androidx.databinding.BaseObservable
import androidx.lifecycle.ViewModel
import com.tigerit.androidhackathonproject.storage.SharedStorage
import com.tigerit.androidhackathonproject.ui.helpers.ResourceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject

open class BaseViewModel() : ViewModel() {
    @Inject lateinit var storage : SharedStorage
    @Inject lateinit var resourceProvider: ResourceProvider
    val ioCoroutine = CoroutineScope(IO)
    val uiCoroutine = CoroutineScope(Main)

}