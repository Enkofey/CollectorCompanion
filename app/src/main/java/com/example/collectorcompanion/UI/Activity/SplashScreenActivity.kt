package com.example.collectorcompanion.UI.Activity

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import com.example.collectorcompanion.R
import kotlin.concurrent.thread
import android.content.Intent


class SplashScreenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        var loader = Loader(this)
        loader.execute()
    }

    private  class Loader(activity: Activity) : AsyncTask<Void,Void,Void>(){
        var activity : Activity
        init {
            this.activity = activity
        }
        override fun doInBackground(vararg params: Void?): Void? {
            Thread.sleep(4000)
            val intent = Intent(activity, MainActivity::class.java)
            this.activity.startActivity(intent)
            return null
        }
    }
}