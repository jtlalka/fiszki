package net.tlalka.android.fiszki.model.dto;

import java.util.List;

import net.tlalka.android.fiszki.model.types.LanguageType;
import org.parceler.Parcel;

@Parcel
public class LanguagesDto {

    protected List<LanguageType> languages;

    public LanguagesDto() {
    }

    public LanguagesDto(List<LanguageType> languages) {
        this.languages = languages;
    }

    public List<LanguageType> getLanguages() {
        return languages;
    }
}
