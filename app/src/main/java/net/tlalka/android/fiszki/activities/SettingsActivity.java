package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import net.tlalka.android.fiszki.R;

public class SettingsActivity extends BaseSetupActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.settings_activity);
    }

    @XmlOnClick
    public void onViewClick(View view) {
        super.finish();
    }
}
