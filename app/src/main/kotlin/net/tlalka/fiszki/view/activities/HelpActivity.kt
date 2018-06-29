package net.tlalka.fiszki.view.activities

import android.os.Bundle
import android.view.View
import butterknife.OnClick
import net.tlalka.fiszki.R

class HelpActivity : BasePageActivity() {

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        super.setContentView(R.layout.help_activity)
        super.activityComponent.inject(this)
    }

    @OnClick(R.id.help_layout)
    fun onViewClick(@Suppress("UNUSED_PARAMETER") view: View) {
        super.finish()
    }
}
