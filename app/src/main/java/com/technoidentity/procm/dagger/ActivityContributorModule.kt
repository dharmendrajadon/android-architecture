package com.technoidentity.procm.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Activity Contributor for publishing all activities
 */
@Module
abstract class ActivityContributorModule {

  @ContributesAndroidInjector(modules = [FragmentContributorModule::class])
  internal abstract fun contributeMainActivity(): MainActivity
}