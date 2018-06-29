package net.tlalka.fiszki.domain.services

import net.tlalka.fiszki.core.annotations.SessionScope
import net.tlalka.fiszki.model.helpers.StorageHelper
import net.tlalka.fiszki.model.types.LanguageType
import net.tlalka.fiszki.model.types.StorageType
import javax.inject.Inject

@SessionScope
class StorageService @Inject constructor() {

    @Inject
    lateinit var storageHelper: StorageHelper

    var isWelcomeView: Boolean
        get() = this.storageHelper.getBoolean(StorageType.WELCOME_VIEW, true)
        set(visible) = this.storageHelper.setBoolean(StorageType.WELCOME_VIEW, visible)

    val language: LanguageType
        get() = this.storageHelper.getEnum(StorageType.LANGUAGE, DEFAULT_LANGUAGE)

    val translation: LanguageType
        get() = this.storageHelper.getEnum(StorageType.TRANSLATION, DEFAULT_TRANSLATION)

    operator fun set(storageType: StorageType, languageType: LanguageType) {
        this.storageHelper.setEnum(storageType, languageType)
    }

    companion object {
        private val DEFAULT_LANGUAGE = LanguageType.EN
        private val DEFAULT_TRANSLATION = LanguageType.PL
    }
}
