package net.tlalka.android.fiszki.models.dto;

import com.google.gson.annotations.SerializedName;
import net.tlalka.android.fiszki.models.types.LevelType;

import java.util.List;

public class LessonDto {

    @SerializedName("name")
    private WordDto name;

    @SerializedName("level")
    private LevelType level;

    @SerializedName("words")
    private List<WordDto> words;

    public WordDto getName() {
        return name;
    }

    public void setName(WordDto name) {
        this.name = name;
    }

    public LevelType getLevel() {
        return level;
    }

    public void setLevel(LevelType level) {
        this.level = level;
    }

    public List<WordDto> getWords() {
        return words;
    }

    public void setWords(List<WordDto> words) {
        this.words = words;
    }
}
