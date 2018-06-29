package net.tlalka.fiszki.domain.controllers

import com.annimon.stream.Collectors
import com.annimon.stream.Stream
import net.tlalka.fiszki.core.annotations.ActivityScope
import net.tlalka.fiszki.domain.services.LessonService
import net.tlalka.fiszki.domain.services.StorageService
import net.tlalka.fiszki.domain.services.WordService
import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.entities.Word
import javax.inject.Inject

@ActivityScope
class ListController @Inject constructor() {

    @Inject
    lateinit var storageService: StorageService

    @Inject
    lateinit var lessonService: LessonService

    @Inject
    lateinit var wordService: WordService

    fun getLessonList(): List<Lesson> {
        return lessonService.getLessons(storageService.language)
    }

    fun getWordsMap(): Map<Long, List<Word>> {
        return Stream
                .of(wordService.getWords(storageService.language))
                .collect(Collectors.groupingBy { w -> w.lesson.id })
    }

    fun getTranslationMap(): Map<Long, Word> {
        return Stream
                .of(wordService.getWords(storageService.translation))
                .collect(Collectors.toMap { w -> w.cluster.id })
    }
}
