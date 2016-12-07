package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;

import static net.tlalka.android.fiszki.utils.ValidUtils.isNotEmpty;
import static net.tlalka.android.fiszki.utils.ValidUtils.isNotNull;

public class WelcomeActivity extends AbstractActivity {

    public static String MESSAGE = "net.tlalka.android.fiszki.welcome.message";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.welcome_activity);

        Bundle sendData = getIntent().getExtras();
        if (isNotNull(sendData)) {
            String viewInfo = sendData.getString(MESSAGE);

            if (isNotEmpty(viewInfo)) {
                TextView textView = (TextView) findViewById(R.id.text_view_info);
                textView.setText(viewInfo);
            }
        }
    }

    public void onViewClick(View view) {
        super.finish();
    }
}
