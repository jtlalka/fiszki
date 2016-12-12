package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.StorageType;

public class SettingsActivity extends BaseSetupActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.settings_activity);

        this.initElements();
        this.initSettings();
    }

    private void initElements() {
    }

    private void initSettings() {
        LanguageType language = super.getStorageHelper().getEnum(StorageType.LANGUAGE, LanguageType.EN);
        LanguageType translation = super.getStorageHelper().getEnum(StorageType.TRANSLATION, LanguageType.PL);
    }

    @XmlOnClick
    public void onViewClick(View view) {
        super.finish();
    }
}
