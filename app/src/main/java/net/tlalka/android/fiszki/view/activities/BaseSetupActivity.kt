package net.tlalka.android.fiszki.view.activities

import android.view.Menu
import android.view.MenuItem

import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.view.elements.SetupElement

abstract class BaseSetupActivity : AbstractActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return createMenu(R.menu.setup_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return SetupElement.triggerAction(this, item.itemId) || super.onOptionsItemSelected(item)
    }
}
