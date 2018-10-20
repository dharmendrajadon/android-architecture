package com.technoidentity.procm.dagger.modules

import android.app.Application
import androidx.room.Room
import com.technoidentity.procm.database.ProCMDb
import com.technoidentity.procm.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Database Module for providing database requirements
 */
@Module
class DatabaseModule {

  /**
   * Room Database Provider
   * @param app Application Context
   * @return {ProCMDb} Returns Room Database
   */
  @Provides
  @Singleton
  internal fun providesDb(app: Application): ProCMDb {
    return Room.databaseBuilder(app, ProCMDb::class.java, "pro_cm.db")
        .fallbackToDestructiveMigration()
        .build()
  }

  /**
   * Task dao Provider
   * @param db Database Context
   * @return {TaskDao} Returns Task Dao
   */
  @Provides
  @Singleton
  internal fun providesTaskDao(db: ProCMDb): TaskDao {
    return db.taskDao()
  }
}