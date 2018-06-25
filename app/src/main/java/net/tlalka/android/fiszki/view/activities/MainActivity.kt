package net.tlalka.android.fiszki.view.activities

import java.util.Arrays
import javax.inject.Inject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import butterknife.BindView

import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.domain.services.StorageService
import net.tlalka.android.fiszki.model.dto.WelcomeDto
import net.tlalka.android.fiszki.view.adapters.MenuAdapter
import net.tlalka.android.fiszki.view.elements.PageElement
import net.tlalka.android.fiszki.view.elements.SetupElement
import net.tlalka.android.fiszki.view.navigations.Navigator

class MainActivity : AbstractActivity() {

    @BindView(R.id.main_list_view)
    lateinit var mainListView: ListView

    @Inject
    lateinit var storageService: StorageService

    @Inject
    lateinit var navigator: Navigator

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        super.setContentView(R.layout.main_activity)
        super.activityComponent.inject(this)

        initStartActivity()
        initMenuListActivity()
    }

    private fun initStartActivity() {
        if (storageService.isWelcomeView) {
            storageService.isWelcomeView = false
            openStartActivity()
        }
    }

    private fun openStartActivity() {
        navigator.openWelcomeActivity(this, WelcomeDto(super.getString(R.string.welcome_message)))
    }

    private fun initMenuListActivity() {
        mainListView.adapter = MenuAdapter(this, Arrays.asList(*PageElement.values()))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.createMenu(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return SetupElement.triggerAction(this, item.itemId) || super.onOptionsItemSelected(item)
    }
}
