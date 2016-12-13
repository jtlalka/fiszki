package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.adapters.LanguageAdapter;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.StorageType;

import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends BaseSetupActivity implements AdapterView.OnItemSelectedListener {

    private Spinner languageSpinner;
    private Spinner translationSpinner;

    private LanguageType language;
    private LanguageType translation;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.settings_activity);

        this.initElements();
        this.initSettings();
        this.runActivity();
    }

    private void initElements() {
        this.languageSpinner = (Spinner) findViewById(R.id.language_spinner);
        this.translationSpinner = (Spinner) findViewById(R.id.translation_spinner);
    }

    private void initSettings() {
        this.language = super.getStorageHelper().getEnum(StorageType.LANGUAGE, LanguageType.EN);
        this.translation = super.getStorageHelper().getEnum(StorageType.TRANSLATION, LanguageType.PL);
    }

    private void runActivity() {
        List<LanguageType> languageTypes = Arrays.asList(LanguageType.values());

        this.languageSpinner.setAdapter(new LanguageAdapter(this, languageTypes));
        this.languageSpinner.setSelection(languageTypes.indexOf(language));
        this.languageSpinner.setOnItemSelectedListener(this);
        this.languageSpinner.setTag(StorageType.LANGUAGE);

        this.translationSpinner.setAdapter(new LanguageAdapter(this, languageTypes));
        this.translationSpinner.setSelection(languageTypes.indexOf(translation));
        this.translationSpinner.setOnItemSelectedListener(this);
        this.translationSpinner.setTag(StorageType.TRANSLATION);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        LanguageType languageType = (LanguageType) parent.getItemAtPosition(position);
        StorageType storageType = (StorageType) parent.getTag();

        super.getStorageHelper().setEnum(storageType, languageType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // not required.
    }
}
