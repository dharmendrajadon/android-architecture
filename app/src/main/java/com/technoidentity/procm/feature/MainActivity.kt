package com.technoidentity.procm.feature

import android.os.Bundle
import com.technoidentity.procm.R
import com.technoidentity.procm.utils.AppPrefs
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var appPrefs: AppPrefs

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    //Toast.makeText(this, appPrefs.userToken, Toast.LENGTH_LONG).show()
  }
}
