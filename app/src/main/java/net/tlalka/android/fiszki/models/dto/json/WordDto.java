package net.tlalka.android.fiszki.models.dto.json;

import net.tlalka.android.fiszki.models.types.LanguageType;

import java.util.HashMap;
import java.util.Set;

public class WordDto extends HashMap<LanguageType, String> {

    public Set<LanguageType> getLanguages() {
        return super.keySet();
    }
}
