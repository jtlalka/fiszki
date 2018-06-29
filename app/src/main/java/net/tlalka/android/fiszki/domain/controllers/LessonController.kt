package net.tlalka.android.fiszki.domain.controllers

import net.tlalka.android.fiszki.core.annotations.ActivityScope
import net.tlalka.android.fiszki.domain.services.CacheService
import net.tlalka.android.fiszki.domain.services.LessonService
import net.tlalka.android.fiszki.domain.services.StorageService
import net.tlalka.android.fiszki.domain.services.WordService
import net.tlalka.android.fiszki.model.dto.LessonDto
import net.tlalka.android.fiszki.model.entities.Lesson
import net.tlalka.android.fiszki.model.entities.Word
import net.tlalka.android.fiszki.model.types.LanguageType
import java.util.Random
import javax.inject.Inject

@ActivityScope
class LessonController
    @Inject constructor(cacheService: CacheService, storageService: StorageService, lessonDto: LessonDto) {

    @Inject
    lateinit var lessonService: LessonService

    @Inject
    lateinit var wordService: WordService

    private val lesson: Lesson = cacheService.getLesson(lessonDto.lessonId)
    private val words: MutableList<Word> = cacheService.getWords(lesson).toMutableList()
    private val language: LanguageType = storageService.language
    private var translation: LanguageType = storageService.translation

    private var activeWord: Word = Word()
    private var correctScore: Int = 0
    private var incorrectScore: Int = 0

    fun getTranslateWord(): Word {
        return wordService.getTranslation(activeWord, translation)
    }

    fun getLanguages(): List<LanguageType> {
        val languages = wordService.getLanguages(activeWord).toMutableList()
        languages.remove(this.language)
        languages.remove(this.translation)
        return languages
    }

    fun getNextWord(): String {
        activeWord = this.generateWord()
        return activeWord.value
    }

    fun getLessonStatus(): String {
        return words.size.toString()
    }

    fun setTranslation(translation: LanguageType) {
        this.translation = translation
    }

    fun hasNextWord(): Boolean {
        return words.isNotEmpty()
    }

    private fun generateWord(): Word {
        val newWord = words[Random().nextInt(words.size)]
        return if (words.size > 1 && newWord == activeWord) generateWord() else newWord
    }

    fun correctAnswer() {
        this.correctScore++
        this.wordService.increaseProgress(activeWord)
        this.words.remove(activeWord)
    }

    fun incorrectAnswer() {
        this.incorrectScore++
        this.wordService.decreaseProgress(activeWord)
    }

    fun updateLessonDto(lessonDto: LessonDto) {
        lessonDto.setScoreValues(correctScore, correctScore, incorrectScore)
    }

    fun updateLessonProgress() {
        this.lessonService.increaseProgress(lesson)
    }
}
