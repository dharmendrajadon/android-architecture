package com.technoidentity.procm.feature.home

import androidx.lifecycle.MutableLiveData
import com.technoidentity.procm.feature.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {
  val message: MutableLiveData<String> = MutableLiveData()

  init {
    message.value = "Old Message"
  }

  fun updateMessage() {
    message.postValue("New Message Is Here")
  }
}