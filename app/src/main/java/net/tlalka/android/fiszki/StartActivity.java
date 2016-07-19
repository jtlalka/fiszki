package net.tlalka.android.fiszki;

import net.tlalka.android.fwork.FworkActivity;
import net.tlalka.android.fwork.FworkInit;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class StartActivity extends FworkActivity {
	
	public static String VIEW_INFO = "viewInfo";
	public static Activity CLASS;
	
	private View view;
	private TextView textView;
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle, R.layout.start_view);
		
		this.view = (View) findViewById(R.id.view);
		this.view.setOnClickListener(new Listener());
		
		this.textView = (TextView) findViewById(R.id.textViewNull);
		this.textView.setText(".....");
		
	    Bundle sendData = getIntent().getExtras();
	    
	    if (FworkInit.Valid.isNotNull(sendData)) {
		    String viewInfo = sendData.getString(VIEW_INFO);
		    
		    if (FworkInit.Valid.isNotEmpty(viewInfo)) {
		    	textView.setText(viewInfo);
		    }
	    }
	    StartActivity.CLASS = this;
	}

	private class Listener implements OnClickListener {
		
		@Override
		public void onClick(View view) {
			CLASS.finish();
		}
	}
}
