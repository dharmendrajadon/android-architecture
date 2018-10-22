package com.technoidentity.procm.feature.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Base ViewModel for initialization and base configurations
 */
abstract class BaseViewModel : ViewModel() {

  // Loading Dialog
  private val isLoading: ObservableBoolean = ObservableBoolean(false)

  // Composite Disposable
  protected var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    private set

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