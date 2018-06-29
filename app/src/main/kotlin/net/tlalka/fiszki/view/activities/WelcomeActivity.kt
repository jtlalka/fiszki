package net.tlalka.fiszki.view.activities

import javax.inject.Inject

import android.os.Bundle
import android.view.View
import android.widget.TextView

import butterknife.BindView
import butterknife.OnClick
import net.tlalka.fiszki.R
import net.tlalka.fiszki.model.dto.WelcomeDto
import net.tlalka.fiszki.view.navigations.Navigator
import net.tlalka.fiszki.view.activities.AbstractActivity

class WelcomeActivity : AbstractActivity() {

    @BindView(R.id.welcome_message)
    lateinit var message: TextView

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var welcomeDto: WelcomeDto

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        super.setContentView(R.layout.welcome_activity)
        super.activityComponent.inject(this)

        message.text = welcomeDto.message
    }

    @OnClick(R.id.welcome_layout)
    fun onLayoutClick(view: View) {
        navigator.finish(this)
    }
}
