package net.tlalka.android.fiszki.domain.services;

import net.tlalka.android.fiszki.core.annotations.SessionScope;
import net.tlalka.android.fiszki.model.helpers.StorageHelper;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.model.types.StorageType;

import javax.inject.Inject;

@SessionScope
public class StorageService {

    private static final LanguageType DEFAULT_LANGUAGE = LanguageType.EN;
    private static final LanguageType DEFAULT_TRANSLATION = LanguageType.PL;

    @Inject
    public StorageService() {
    }

    @Inject
    protected StorageHelper storageHelper;

    public boolean isWelcomeView() {
        return this.storageHelper.getBoolean(StorageType.WELCOME_VIEW, true);
    }

    public void setWelcomeView(boolean value) {
        this.storageHelper.setBoolean(StorageType.WELCOME_VIEW, value);
    }

    public LanguageType getLanguage() {
        return this.storageHelper.getEnum(StorageType.LANGUAGE, DEFAULT_LANGUAGE);
    }

    public LanguageType getTranslation() {
        return this.storageHelper.getEnum(StorageType.TRANSLATION, DEFAULT_TRANSLATION);
    }

    public void set(StorageType storageType, LanguageType languageType) {
        this.storageHelper.setEnum(storageType, languageType);
    }
}
