package com.technoidentity.procm.feature.base

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.technoidentity.procm.BR
import com.technoidentity.procm.ui.LoadingDialog
import dagger.android.support.DaggerAppCompatActivity

/**
 * Base Activity for initialization and base configurations
 */
abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : DaggerAppCompatActivity() {

  /**
   *  Base Binding
   */
  private var binding: B? = null
  /**
   *  For Injecting Base View Model
   */
  private var viewModel: VM? = null

  /**
   *  Used for registering layout with activity
   */
  @LayoutRes
  abstract fun getLayoutId(): Int

  /**
   *  Used for registering viewModel with activity
   */
  abstract fun getViewModel(): VM

  // For Progress Dialog
  private var progressDialog: LoadingDialog? = null

  /**
   * When Activity is Created
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Initialize all bindings here
    this.initializeBinding()
  }

  /**
   * Binding Initialization
   */
  private fun initializeBinding() {

    // View DataBinding Init
    this.binding = DataBindingUtil.setContentView(this, getLayoutId())

    // ViewModel Init
    this.viewModel = viewModel ?: getViewModel()

    // DataBinding
    this.binding?.setVariable(BR.viewModel, this.viewModel)
    this.binding?.setLifecycleOwner(this)
  }

  /**
   * Hide Keyboard
   */
  protected fun hideKeyboard() {
    val view = this.currentFocus

    if (view != null) {
      val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager

      inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }

  /**
   * Show Progress Dialog
   */
  protected fun showLoading(message: String? = "") {
    this.progressDialog = this.showProgressDialog(message)
  }

  /**
   * Hide Progress Dialog
   */
  protected fun hideLoading() {

    if (this.progressDialog != null && this.progressDialog?.isShowing == true) {
      progressDialog?.cancel()
    }
  }


  override fun onPause() {

    // Hide Keyboard
    this.hideKeyboard()

    // Call Activity Pause
    super.onPause()
  }

  /**
   * Progress Dialog Indeterminate
   */
  private fun showProgressDialog(message: String?): LoadingDialog {

    // Create a dialog
    val progressDialog = LoadingDialog(this, message)
    progressDialog.show()
    return progressDialog
  }
}