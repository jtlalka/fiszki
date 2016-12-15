package net.tlalka.android.fiszki.models.dto;

import net.tlalka.android.fiszki.models.types.LanguageType;
import org.parceler.Parcel;

import java.util.List;

@Parcel
public class LanguagesDto {

    List<LanguageType> languages;

    public LanguagesDto() {
    }

    public LanguagesDto(List<LanguageType> languages) {
        this.languages = languages;
    }

    public List<LanguageType> getLanguages() {
        return languages;
    }
}
