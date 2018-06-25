package net.tlalka.android.fiszki.view.activities

import java.util.Arrays
import javax.inject.Inject

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

import butterknife.BindView
import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.domain.services.StorageService
import net.tlalka.android.fiszki.model.types.LanguageType
import net.tlalka.android.fiszki.model.types.StorageType
import net.tlalka.android.fiszki.view.adapters.LanguageAdapter

class SettingsActivity : BaseSetupActivity(), AdapterView.OnItemSelectedListener {

    @BindView(R.id.settings_language_spinner)
    lateinit var languageSpinner: Spinner

    @BindView(R.id.settings_translation_spinner)
    lateinit var translationSpinner: Spinner

    @Inject
    lateinit var storageService: StorageService

    private var language: LanguageType? = null
    private var translation: LanguageType? = null

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        super.setContentView(R.layout.settings_activity)
        super.activityComponent.inject(this)

        initStorage()
        runActivity()
    }

    private fun initStorage() {
        language = storageService.language
        translation = storageService.translation
    }

    private fun runActivity() {
        val languageTypes = Arrays.asList(*LanguageType.values())

        languageSpinner.adapter = LanguageAdapter(this, languageTypes)
        languageSpinner.setSelection(languageTypes.indexOf(language))
        languageSpinner.onItemSelectedListener = this
        languageSpinner.tag = StorageType.LANGUAGE

        translationSpinner.adapter = LanguageAdapter(this, languageTypes)
        translationSpinner.setSelection(languageTypes.indexOf(translation))
        translationSpinner.onItemSelectedListener = this
        translationSpinner.tag = StorageType.TRANSLATION
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val languageType = parent.getItemAtPosition(position) as LanguageType
        val storageType = parent.tag as StorageType

        storageService.set(storageType, languageType)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // not required.
    }
}
