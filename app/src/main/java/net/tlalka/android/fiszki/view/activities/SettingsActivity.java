package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import butterknife.BindView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.helpers.StorageHelper;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.model.types.StorageType;
import net.tlalka.android.fiszki.view.adapters.LanguageAdapter;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends BaseSetupActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.language_spinner)
    protected Spinner languageSpinner;

    @BindView(R.id.translation_spinner)
    protected Spinner translationSpinner;

    @Inject
    protected StorageHelper storageHelper;

    private LanguageType language;
    private LanguageType translation;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.settings_activity);
        super.getActivityComponent().inject(this);

        this.initStorage();
        this.runActivity();
    }

    private void initStorage() {
        this.language = this.storageHelper.getEnum(StorageType.LANGUAGE, LanguageType.EN);
        this.translation = this.storageHelper.getEnum(StorageType.TRANSLATION, LanguageType.PL);
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

        this.storageHelper.setEnum(storageType, languageType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // not required.
    }
}
