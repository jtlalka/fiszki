package net.tlalka.android.fiszki.domain.controllers;

import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.domain.services.LessonService;
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

    private LessonService lessonService;
    private WordService wordService;

    private Lesson lesson;
    private List<Word> words;
    private Word activeWord;

    @Inject
    public LessonController(LessonService lessonService, WordService wordService, LessonDto lessonDto) {
        this.lessonService = lessonService;
        this.wordService = wordService;

        this.lesson = lessonService.getLesson(lessonDto.getLessonId());
        this.words = wordService.getWords(lesson);
    }

    public Word getTranslation(LanguageType languageType) {
        return wordService.getTranslation(activeWord, languageType);
    }

    public List<LanguageType> getLanguages() {
        return wordService.getLanguages(activeWord);
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
