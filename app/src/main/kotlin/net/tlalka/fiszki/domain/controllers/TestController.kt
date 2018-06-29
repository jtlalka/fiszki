package net.tlalka.fiszki.domain.controllers

import net.tlalka.fiszki.core.annotations.ActivityScope
import net.tlalka.fiszki.domain.services.CacheService
import net.tlalka.fiszki.domain.services.LessonService
import net.tlalka.fiszki.domain.services.StorageService
import net.tlalka.fiszki.domain.services.WordService
import net.tlalka.fiszki.domain.utils.ValidUtils
import net.tlalka.fiszki.model.dto.LessonDto
import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.entities.Word
import net.tlalka.fiszki.model.types.LanguageType
import java.util.ArrayList
import java.util.Random
import javax.inject.Inject

@ActivityScope
class TestController
    @Inject constructor(cacheService: CacheService, storageService: StorageService, lessonDto: LessonDto) {

    @Inject
    lateinit var lessonService: LessonService

    @Inject
    lateinit var wordService: WordService

    private val lesson: Lesson = cacheService.getLesson(lessonDto.lessonId)
    private val words: MutableList<Word> = cacheService.getWords(lesson).toMutableList()
    private val language: LanguageType = storageService.language
    private var translation: LanguageType = storageService.translation

    private var answers: MutableList<String> = emptyList<String>().toMutableList()
    private var correctAnswer: String = ""
    private var activeWord: Word = Word()
    private var correctScore: Int = 0
    private var incorrectScore: Int = 0

    var wordIndex: Int = 0
        private set

    init {
        this.randomiseCollection(words)
    }

    fun getLanguages(): List<LanguageType> {
        val languages = this.wordService.getLanguages(this.activeWord).toMutableList()
        languages.remove(this.language)
        languages.remove(this.translation)
        return languages
    }

    fun getNextWord(): String {
        this.activeWord = this.words[wordIndex++]
        return this.activeWord.value
    }

    fun getTestSize(): Int {
        return this.words.size
    }

    fun getThisAnswers(): List<String> {
        val answer = this.getTranslateWord(this.activeWord)

        return if (ValidUtils.isNotNull(answer)) {
            this.correctAnswer = answer.value
            this.answers = ArrayList()
            this.answers.add(correctAnswer)

            this.generateAnswers(answers, 3)
            this.randomiseCollection(answers)
            answers
        } else {
            emptyList()
        }
    }

    private fun generateAnswers(answers: MutableList<String>, size: Int) {
        if (size > 0 && this.generateAnswer(answers, 100)) {
            this.generateAnswers(answers, size - 1)
        }
    }

    private fun generateAnswer(answers: MutableList<String>, repetitions: Int): Boolean {
        val word = words[Random().nextInt(words.size)]
        val value = this.getTranslateWord(word).value

        return if (repetitions - 1 > 0) {
            if (answers.contains(value)) generateAnswer(answers, repetitions - 1) else answers.add(value)
        } else {
            answers.add("...")
        }
    }

    private fun getTranslateWord(word: Word): Word {
        return this.wordService.getTranslation(word, this.translation)
    }

    private fun randomiseCollection(list: MutableList<*>) {
        list.shuffle()
    }

    fun setTranslation(translation: LanguageType) {
        this.translation = translation
    }

    fun hasNextWord(): Boolean {
        return this.wordIndex < this.words.size
    }

    fun validAnswer(index: Int): Boolean {
        return if (this.answers.indexOf(this.correctAnswer) == index) {
            this.correctScore++
            true
        } else {
            this.incorrectScore++
            false
        }
    }

    fun updateLessonDto(lessonDto: LessonDto) {
        lessonDto.setScoreValues(calculateScore(), correctScore, incorrectScore)
    }

    fun updateBestScore() {
        val newScore = this.calculateScore()
        if (newScore > this.lesson.score) {
            this.lessonService.updateScore(lesson, newScore)
        }
    }

    private fun calculateScore(): Int {
        return Math.round(Math.max((correctScore - incorrectScore).toFloat(), 0.0f) * 100 / correctScore)
    }
}
