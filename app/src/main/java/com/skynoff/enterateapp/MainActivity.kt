package com.skynoff.enterateapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.skynoff.enterateapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        suscribeToGeneralNotifications()
    }
    private fun suscribeToGeneralNotifications(){
        FirebaseMessaging.getInstance().subscribeToTopic("general").addOnCompleteListener {
            var msg = "SUSCRITO"
            if (!it.isSuccessful) {
                msg = " No SUSCRIBIO"
            }
            Log.i("Subscription Status", msg)
        }
    }
}
