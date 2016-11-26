package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fwork.FworkInit;

public class StartActivity extends AbstractActivity {

    public static String MESSAGE = "net.tlalka.android.fiszki.start.message";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.start_view);

        Bundle sendData = getIntent().getExtras();
        if (FworkInit.Valid.isNotNull(sendData)) {
            String viewInfo = sendData.getString(MESSAGE);

            if (FworkInit.Valid.isNotEmpty(viewInfo)) {
                TextView textView = (TextView) findViewById(R.id.text_view_info);
                textView.setText(viewInfo);
            }
        }
    }

    public void onViewClick(View view) {
        super.finish();
    }
}
