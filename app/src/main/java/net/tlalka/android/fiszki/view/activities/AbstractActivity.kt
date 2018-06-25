package net.tlalka.android.fiszki.view.activities

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.widget.Toast

import net.tlalka.android.fiszki.core.AppFiszki
import net.tlalka.android.fiszki.core.components.ActivityComponent

abstract class AbstractActivity : Activity() {

    protected val activityComponent: ActivityComponent by lazy {
        (application as AppFiszki).getActivityComponent(this)
    }

    fun startActivity(classValue: Class<*>) {
        super.startActivity(Intent(super.getApplicationContext(), classValue))
    }

    fun createMenu(menuResource: Int, menu: Menu): Boolean {
        super.getMenuInflater().inflate(menuResource, menu)
        return true
    }

    fun alert(message: String) {
        this.alert(message, Toast.LENGTH_SHORT)
    }

    fun alert(message: String, timeToast: Int) {
        Toast.makeText(super.getBaseContext(), message, timeToast).show()
    }
}
