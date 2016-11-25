package net.tlalka.android.fiszki.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fwork.FworkInit;

public class StartActivity extends AbstractActivity {

    public static String VIEW_INFO = "viewInfo";
    public static Activity CLASS;

    private View view;
    private TextView textView;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.start_view);

        this.view = findViewById(R.id.view);
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
