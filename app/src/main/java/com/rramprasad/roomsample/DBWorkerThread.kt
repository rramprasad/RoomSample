package com.rramprasad.roomsample

import android.os.Handler
import android.os.HandlerThread

class DBWorkerThread(val threadName : String) : HandlerThread(threadName){


    private lateinit var mWorkerHandler: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task : Runnable){
        mWorkerHandler.post(task)
    }
}