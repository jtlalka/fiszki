package net.tlalka.android.fiszki;

import net.tlalka.android.fwork.FworkActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class HelpActivity extends FworkActivity {

	private View view;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle, R.layout.help_view);
		
		this.view = (View) findViewById(R.id.view);
		this.view.setOnClickListener(new PrivListener(this));
	}

	private class PrivListener implements OnClickListener {
		private Context context;
		
		public PrivListener(Context context) {
			this.context = context;
		}
		
		@Override
		public void onClick(View view) {
			((HelpActivity) this.context).finish();
		}
	}
}
