package com.axe.roomdome.db

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

import com.axe.roomdome.bean.User
import com.axe.roomdome.dao.UserDao


@Database(entities = [User::class], version = 2)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(AppDataBase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "app_db"
                    )
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE User ADD COLUMN hobby TEXT")
            }
        }
    }




}
