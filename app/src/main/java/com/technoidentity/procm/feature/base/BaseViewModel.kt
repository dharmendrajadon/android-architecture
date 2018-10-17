package com.technoidentity.procm.feature.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

  // Loading Dialog
  private val isLoading: ObservableBoolean = ObservableBoolean(false)

  // Composite Disposable
  private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

  /**
   * On ViewModel Cleared
   */
  override fun onCleared() {

    // Clear All Disposables
    this.compositeDisposable?.clear()
    this.compositeDisposable = null

    // Run Super OnCleared()
    super.onCleared()
  }

  /**
   * Get Composite Disposable
   */
  fun getCompositeDisposable(): CompositeDisposable? {
    return this.compositeDisposable
  }

  /**
   * Get IsView Loading
   */
  fun getIsLoading(): ObservableBoolean {
    return this.isLoading
  }

  /**
   * Set View Loading True
   */
  fun setIsLoading(value: Boolean) {
    this.setIsLoading(value)
  }

}