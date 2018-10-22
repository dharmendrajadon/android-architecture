package com.technoidentity.procm.feature.login

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.technoidentity.procm.R
import com.technoidentity.procm.databinding.LoginFragmentBinding
import com.technoidentity.procm.feature.base.BaseFragment
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginViewModel, LoginFragmentBinding>() {

  /**
   * Get ViewModel Factory From DI
   */
  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  companion object {
    fun newInstance() = LoginFragment()
  }

  override fun getLayoutId(): Int {
    return R.layout.login_fragment
  }

  override fun getViewModel(): LoginViewModel {
    return ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
  }

}
