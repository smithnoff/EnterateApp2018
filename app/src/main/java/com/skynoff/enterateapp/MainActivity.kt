package com.skynoff.enterateapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.google.firebase.messaging.FirebaseMessaging
import com.skynoff.enterateapp.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_fragment.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "   ${getString(R.string.app_name)}"
        supportActionBar?.setIcon(R.mipmap.ic_logo_e24)
        suscribeToGeneralNotifications()

    }

    private fun suscribeToGeneralNotifications() {
        FirebaseMessaging.getInstance().subscribeToTopic("general").addOnCompleteListener {
            var msg = "SUSCRITO"
            if (!it.isSuccessful) {
                msg = " No SUSCRIBIO"
            }
            Log.i("Subscription Status", msg)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_section -> {
                selector(
                    "Selccione un categoria", listOf(
                        "Nacionales",
                        "Internacionales",
                        "Economía",
                        "Investigación",
                        "Salud",
                        "Deportes",
                        "Variedades",
                        "Ciencias",
                        "Tal día como hoy"
                    )
                ) { _, i ->
                    loadUrlSelected(i)

                }
            }
            R.id.menu_notification -> {
                startActivity(Intent(this, NotificationsActivity::class.java))
            }
            R.id.menu_profile -> {

            }
            R.id.menu_home -> {
                loadUrlSelected(-1)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadUrlSelected(i: Int) {
        val url = when (i) {
            -1 -> main_webview.url
            0 -> "https://enterate24.com/category/nacionales/"
            1 -> "https://enterate24.com/category/internacionales/"
            2 -> "https://enterate24.com/category/economia/"
            3 -> "https://enterate24.com/category/investigacion/"
            4 -> "https://enterate24.com/category/salud/"
            5 -> "https://enterate24.com/category/deportes/"
            6 -> "https://enterate24.com/category/variedades/"
            7 -> "https://enterate24.com/category/ciencias/"
            8 -> "https://enterate24.com/category/tal-dia-como-hoy/"
            else -> "https://enterate24.com/"
        }
        main_webview.loadUrl(url)
        main_viewflipper.displayedChild = 0
    }

    override fun onBackPressed() {
        if (main_webview.canGoBack() && main_webview.url != "https://enterate24.com/") {
            main_webview.loadUrl("https://enterate24.com/")
            main_viewflipper.displayedChild = 0
        } else {
            super.onBackPressed()
        }

    }
}
