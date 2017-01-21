package net.tlalka.android.fiszki.domain.controllers;

import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.domain.services.CacheService;
import net.tlalka.android.fiszki.domain.services.LessonService;
import net.tlalka.android.fiszki.domain.services.StorageService;
import net.tlalka.android.fiszki.domain.services.WordService;
import net.tlalka.android.fiszki.domain.utils.ValidUtils;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;
import net.tlalka.android.fiszki.model.types.LanguageType;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@ActivityScope
public class TestController {

    @Inject
    protected LessonService lessonService;

    @Inject
    protected WordService wordService;

    private Lesson lesson;
    private List<Word> words;
    private List<String> answers;
    private String correctAnswer;
    private Word activeWord;

    private LanguageType language;
    private LanguageType translation;

    private int wordIndex;
    private int correctScore;
    private int incorrectScore;

    @Inject
    public TestController(CacheService cacheService, StorageService storageService, LessonDto lessonDto) {
        this.lesson = cacheService.getLesson(lessonDto.getLessonId());
        this.words = cacheService.getWords(lesson);
        this.language = storageService.getLanguage();
        this.translation = storageService.getTranslation();
        this.randomiseCollection(words);
    }

    public List<String> getAnswers() {
        Word answer = this.getTranslateWord(this.activeWord);

        if (ValidUtils.isNotNull(answer)) {
            this.correctAnswer = answer.getValue();
            this.answers = new ArrayList<>();
            this.answers.add(correctAnswer);

            this.generateAnswers(answers, 3);
            this.randomiseCollection(answers);
            return answers;
        } else {
            return Collections.emptyList();
        }
    }

    private void generateAnswers(List<String> answers, int size) {
        if (size > 0 && this.generateAnswer(answers)) {
            this.generateAnswers(answers, --size);
        }
    }

    private boolean generateAnswer(List<String> answers) {
        Word word = words.get(new Random().nextInt(words.size()));
        String value = this.getTranslateWord(word).getValue();
        return answers.contains(value) ? generateAnswer(answers) : answers.add(value);
    }

    private Word getTranslateWord(Word word) {
        return this.wordService.getTranslation(word, this.translation);
    }

    private void randomiseCollection(List<?> list) {
        Collections.shuffle(list);
    }

    public List<LanguageType> getLanguages() {
        List<LanguageType> languages = this.wordService.getLanguages(this.activeWord);
        languages.remove(this.language);
        languages.remove(this.translation);
        return languages;
    }

    public void setTranslation(LanguageType translation) {
        this.translation = translation;
    }

    public boolean hasNextWord() {
        return this.wordIndex < this.words.size();
    }

    public String getNextWord() {
        this.activeWord = this.words.get(wordIndex++);
        return this.activeWord.getValue();
    }

    public int getWordIndex() {
        return this.wordIndex;
    }

    public int getTestSize() {
        return this.words.size();
    }

    public boolean validAnswer(int index) {
        if (this.answers.indexOf(this.correctAnswer) == index) {
            this.correctScore++;
            return true;
        } else {
            this.incorrectScore++;
            return false;
        }
    }

    public void updateLessonDto(LessonDto lessonDto) {
        lessonDto.setScoreValues(calculateScore(), correctScore, incorrectScore);
    }

    public void updateBestScore() {
        int newScore = this.calculateScore();
        if (newScore > this.lesson.getScore()) {
            this.lessonService.updateScore(lesson, newScore);
        }
    }

    private int calculateScore() {
        return Math.round(Math.max(correctScore - incorrectScore, 0.0F) * 100 / correctScore);
    }
}
