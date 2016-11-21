package net.tlalka.android.fiszki.listeners;

import net.tlalka.android.fiszki.elements.MenuElement;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class MenuListener implements OnClickListener {

	private Context context;
	private MenuElement menuElement;

	public MenuListener(Context context, MenuElement menuElement) {
		this.context = context;
		this.menuElement = menuElement;
	}

	@Override
	public void onClick(View view) {
		context.startActivity(new Intent(context.getApplicationContext(), menuElement.getClassValue()));
	}
}
