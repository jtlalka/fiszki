package net.tlalka.android.fiszki.domain.controllers;

import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.domain.services.LessonService;
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

    private LessonService lessonService;
    private WordService wordService;

    private Lesson lesson;
    private List<Word> words;
    private List<String> answers;
    private String correctAnswer;
    private Word activeWord;

    private int wordIndex;
    private int correctScore;
    private int incorrectScore;

    @Inject
    public TestController(LessonService lessonService, WordService wordService, LessonDto lessonDto) {
        this.lessonService = lessonService;
        this.wordService = wordService;

        this.lesson = lessonService.getLesson(lessonDto.getLessonId());
        this.words = wordService.getWords(lesson);

        this.randomiseCollection(words);
    }

    private void randomiseCollection(List<?> list) {
        Collections.shuffle(list);
    }

    public List<String> getAnswers(LanguageType languageType) {
        Word translation = this.getTranslation(this.activeWord, languageType);

        if (ValidUtils.isNotNull(translation)) {
            this.correctAnswer = translation.getValue();
            this.answers = new ArrayList<>();
            this.answers.add(correctAnswer);

            this.generateAnswers(answers, languageType, 3);
            this.randomiseCollection(answers);
            return answers;
        } else {
            return Collections.emptyList();
        }
    }

    private void generateAnswers(List<String> answers, LanguageType languageType, int size) {
        if (size > 0 && this.generateAnswer(answers, languageType)) {
            this.generateAnswers(answers, languageType, --size);
        }
    }

    private boolean generateAnswer(List<String> answers, LanguageType languageType) {
        Word word = words.get(new Random().nextInt(words.size()));
        Word translation = this.getTranslation(word, languageType);

        if (answers.contains(translation.getValue())) {
            return generateAnswer(answers, languageType);
        } else {
            return answers.add(translation.getValue());
        }
    }

    private Word getTranslation(Word word, LanguageType languageType) {
        return this.wordService.getTranslation(word, languageType);
    }

    public List<LanguageType> getLanguages() {
        return this.wordService.getLanguages(this.activeWord);
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

    public int getWordSize() {
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

    public void updateTestScore() {
        int newScore = this.calculateScore();
        if (newScore > this.lesson.getScore()) {
            this.lessonService.updateScore(lesson, newScore);
        }
    }

    private int calculateScore() {
        return (this.correctScore + this.incorrectScore) / this.correctScore * 100;
    }
}
