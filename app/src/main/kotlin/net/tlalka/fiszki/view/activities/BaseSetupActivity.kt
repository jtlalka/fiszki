package net.tlalka.fiszki.view.activities

import android.view.Menu
import android.view.MenuItem
import net.tlalka.fiszki.R
import net.tlalka.fiszki.view.elements.SetupElement

abstract class BaseSetupActivity : AbstractActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return createMenu(R.menu.setup_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return SetupElement.triggerAction(this, item.itemId) || super.onOptionsItemSelected(item)
    }
}
