package net.tlalka.android.fiszki.domain.controllers;

import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.domain.services.CacheService;
import net.tlalka.android.fiszki.domain.services.LessonService;
import net.tlalka.android.fiszki.domain.services.StorageService;
import net.tlalka.android.fiszki.domain.services.WordService;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;
import net.tlalka.android.fiszki.model.types.LanguageType;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@ActivityScope
public class LessonController {

    @Inject
    protected LessonService lessonService;

    @Inject
    protected WordService wordService;

    private Lesson lesson;
    private List<Word> words;
    private Word activeWord;

    private LanguageType language;
    private LanguageType translation;

    @Inject
    public LessonController(CacheService cacheService, StorageService storageService, LessonDto lessonDto) {
        this.lesson = cacheService.getLesson(lessonDto.getLessonId());
        this.words = cacheService.getWords(lesson);

        this.language = storageService.getLanguage();
        this.translation = storageService.getTranslation();
    }

    public Word getTranslateWord() {
        return wordService.getTranslation(activeWord, translation);
    }

    public List<LanguageType> getLanguages() {
        List<LanguageType> languages = wordService.getLanguages(activeWord);
        languages.remove(this.language);
        languages.remove(this.translation);
        return languages;
    }

    public void setTranslation(LanguageType translation) {
        this.translation = translation;
    }

    public boolean hasNextWord() {
        return this.words.size() > 0;
    }

    public String getNextWord() {
        this.activeWord = this.generateWord();
        return this.activeWord.getValue();
    }

    public String getLessonStatus() {
        return String.valueOf(this.words.size());
    }

    private Word generateWord() {
        Word newWord = words.get(new Random().nextInt(words.size()));
        return (words.size() > 1 && newWord == activeWord) ? generateWord() : newWord;
    }

    public void correctAnswer() {
        this.wordService.increaseProgress(activeWord);
        this.words.remove(activeWord);
    }

    public void incorrectAnswer() {
        this.wordService.decreaseProgress(activeWord);
    }

    public void updateLessonProgress() {
        this.lessonService.increaseProgress(lesson);
    }
}
