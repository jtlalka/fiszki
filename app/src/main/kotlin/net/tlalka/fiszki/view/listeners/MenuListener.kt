package net.tlalka.fiszki.view.listeners

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.View.OnClickListener

import net.tlalka.fiszki.view.elements.PageElement

class MenuListener(private val context: Context, private val pageElement: PageElement) : OnClickListener {

    override fun onClick(view: View) {
        context.startActivity(Intent(context.applicationContext, pageElement.getActivityClass()))
    }
}
