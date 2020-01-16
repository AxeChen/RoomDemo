package com.axe.roomdome

import android.app.Application
import com.axe.roomdome.db.AppDataBase

class RoomDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDataBase.getInstance(this)
    }
}