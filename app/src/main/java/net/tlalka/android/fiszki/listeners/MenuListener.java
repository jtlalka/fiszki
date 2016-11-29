package net.tlalka.android.fiszki.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import net.tlalka.android.fiszki.elements.PageElement;


public class MenuListener implements OnClickListener {

    private Context context;
    private PageElement pageElement;

    public MenuListener(Context context, PageElement pageElement) {
        this.context = context;
        this.pageElement = pageElement;
    }

    @Override
    public void onClick(View view) {
        context.startActivity(new Intent(context.getApplicationContext(), pageElement.getActivityClass()));
    }
}
