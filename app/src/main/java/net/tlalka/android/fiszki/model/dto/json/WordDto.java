package net.tlalka.android.fiszki.model.dto.json;

import java.util.HashMap;
import java.util.Set;

import net.tlalka.android.fiszki.model.types.LanguageType;

public class WordDto extends HashMap<LanguageType, String> {

    public Set<LanguageType> getLanguages() {
        return super.keySet();
    }
}
