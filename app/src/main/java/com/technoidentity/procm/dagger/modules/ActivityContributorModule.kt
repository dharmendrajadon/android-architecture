package com.technoidentity.procm.dagger.modules

import com.technoidentity.procm.feature.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Activity Contributor for publishing all activities
 */
@Module
abstract class ActivityContributorModule {

  @ContributesAndroidInjector
  internal abstract fun contributeHomeActivity(): HomeActivity
}