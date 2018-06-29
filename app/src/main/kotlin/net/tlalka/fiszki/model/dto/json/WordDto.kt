package net.tlalka.fiszki.model.dto.json

import net.tlalka.fiszki.model.types.LanguageType

class WordDto : HashMap<LanguageType, String>() {

    fun getLanguages(): Set<LanguageType> = super.keys
}
