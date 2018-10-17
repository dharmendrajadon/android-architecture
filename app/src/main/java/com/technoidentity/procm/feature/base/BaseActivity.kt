package com.technoidentity.procm.feature.base

import android.app.Activity
import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.inputmethod.InputMethodManager
import com.technoidentity.procm.BR
import dagger.android.support.DaggerAppCompatActivity


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
  @Suppress("DEPRECATION")
  private var progressDialog: android.app.ProgressDialog? = null

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
    this.binding?.executePendingBindings()

    // Progress Dialog
    @Suppress("DEPRECATION")
    progressDialog = android.app.ProgressDialog(this)
    progressDialog?.setCancelable(false)
    progressDialog?.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER)
    progressDialog?.isIndeterminate = true
  }

  /**
   * Hide Keyboard
   */
  protected fun hideKeyboard() {
    val view = this.currentFocus

    if (view != null) {
      val inputMethodManager = getSystemService(
          Activity.INPUT_METHOD_SERVICE) as? InputMethodManager

      inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }

  /**
   * Show Progress Dialog
   */
  protected fun showLoading() {
    this.progressDialog = this.showProgressDialog()
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
  @Suppress("DEPRECATION")
  fun showProgressDialog(): ProgressDialog {

    // Create a dialog
    val progressDialog = ProgressDialog(this)
    progressDialog.show()
    if (progressDialog.window != null) {
      progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER)
    progressDialog.isIndeterminate = true
    progressDialog.setCancelable(false)
    progressDialog.setCanceledOnTouchOutside(false)
    return progressDialog
  }
}