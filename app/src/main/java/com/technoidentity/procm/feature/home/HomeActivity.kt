package com.technoidentity.procm.feature.home

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.technoidentity.procm.R
import com.technoidentity.procm.databinding.ActivityHomeBinding
import com.technoidentity.procm.feature.base.BaseActivity
import com.technoidentity.procm.utils.AppPrefs
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  @Inject
  lateinit var appPrefs: AppPrefs

  override fun getLayoutId(): Int {
    return R.layout.activity_home
  }

  override fun getViewModel(): HomeViewModel {
    return ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
  }

}