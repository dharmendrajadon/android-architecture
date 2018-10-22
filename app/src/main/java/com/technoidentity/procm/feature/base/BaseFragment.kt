package com.technoidentity.procm.feature.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.technoidentity.procm.BR
import dagger.android.support.DaggerFragment

/**
 * Base Fragment for initialization and base configurations
 */
abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : DaggerFragment() {

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

  /**
   * On Fragment Create
   * Assign View model property
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // ViewModel Init
    this.viewModel = viewModel ?: getViewModel()

    // Doesn't populate options menu
    setHasOptionsMenu(false)
  }

  /**
   * On Create View
   * Inflate View and return ViewRoot using DataBinding
   */
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {

    // View DataBinding Init
    this.binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

    return binding?.root
  }

  /**
   * On View Created
   * Set DataBinding ViewModel
   */
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // DataBinding
    this.binding?.setVariable(BR.viewModel, this.viewModel)
    this.binding?.setLifecycleOwner(this)
  }
}