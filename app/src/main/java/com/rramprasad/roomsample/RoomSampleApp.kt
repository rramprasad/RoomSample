package com.rramprasad.roomsample

import android.app.Application
import com.facebook.stetho.Stetho

class RoomSampleApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }
}