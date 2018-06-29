package net.tlalka.android.fiszki.model.dto.json

import net.tlalka.android.fiszki.model.types.LanguageType

class WordDto : HashMap<LanguageType, String>() {

    fun getLanguages(): Set<LanguageType> = super.keys
}
