package net.tlalka.android.fiszki.models.dto;

import net.tlalka.android.fiszki.models.types.LevelType;

import java.util.List;

public class LessonDto {

    private WordDto name;

    private LevelType level;

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
