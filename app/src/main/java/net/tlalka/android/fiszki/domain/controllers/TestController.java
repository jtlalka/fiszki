package net.tlalka.android.fiszki.domain.controllers;

import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.domain.services.LessonService;
import net.tlalka.android.fiszki.domain.services.WordService;
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
    private List<Word> answers;
    private Word correctAnswer;

    private int wordIndex;
    private int correctScore;
    private int incorrectScore;

    @Inject
    public TestController(LessonService lessonService, WordService wordService, LessonDto lessonDto) {
        this.lessonService = lessonService;
        this.wordService = wordService;

        this.lesson = lessonService.getLesson(lessonDto.getLessonId());
        this.words = wordService.getWords(lesson);

        Collections.shuffle(words);
    }

    public List<Word> getAnswers(LanguageType languageType) {
        this.correctAnswer = this.getTranslation(this.getActiveWord(), languageType);

        if (correctAnswer != null) {
            this.answers = new ArrayList<>();
            this.answers.add(correctAnswer);

            this.answers = this.generateAnswerList(answers, languageType, 3);
            return answers;
        } else {
            return Collections.emptyList();
        }
    }

    private List<Word> generateAnswerList(List<Word> answers, LanguageType languageType, int size) {
        if (size > 0 && this.generateAnswer(answers, languageType)) {
            return this.generateAnswerList(answers, languageType, --size);
        } else {
            return answers;
        }
    }

    private boolean generateAnswer(List<Word> answers, LanguageType languageType) {
        Word word = words.get(new Random().nextInt(words.size()));
        Word translation = this.getTranslation(word, languageType);

        if (answers.contains(translation)) {
            return generateAnswer(answers, languageType);
        } else {
            return answers.add(translation);
        }
    }

    private Word getTranslation(Word word, LanguageType languageType) {
        return this.wordService.getTranslation(word, languageType);
    }

    public List<LanguageType> getLanguages() {
        return this.wordService.getLanguages(this.getActiveWord());
    }

    public boolean validAnswer(int index) {
        return this.answers.indexOf(this.correctAnswer) == index;
    }

    private Word getActiveWord() {
        return this.words.get(wordIndex);
    }

    public boolean hasNextWord() {
        return this.wordIndex < this.words.size();
    }

    public String getNextWord() {
        return this.words.get(wordIndex++).getValue();
    }

    public void correctAnswer() {
        this.correctScore++;
    }

    public void incorrectAnswer() {
        this.incorrectScore++;
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
