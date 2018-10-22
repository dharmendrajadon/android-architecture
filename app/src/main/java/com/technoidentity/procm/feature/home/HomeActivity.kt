package com.technoidentity.procm.feature.home

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.technoidentity.procm.R
import com.technoidentity.procm.databinding.ActivityHomeBinding
import com.technoidentity.procm.feature.base.BaseActivity
import com.technoidentity.procm.utils.AppPrefs
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Home Activity(Main Screen)
 */
class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

  /**
   * Get ViewModel Factory From DI
   */
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

  override fun onStart() {
    super.onStart()
    showLoading()
  }

  @SuppressLint("CheckResult")
  override fun onResume() {
    super.onResume()

    Completable.timer(5, TimeUnit.SECONDS)
        .observeOn(Schedulers.io())
        .subscribe {
          hideLoading()
          getViewModel().updateMessage()
        }
  }

}