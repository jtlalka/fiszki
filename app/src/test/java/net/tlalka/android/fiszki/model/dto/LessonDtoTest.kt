package net.tlalka.android.fiszki.model.dto

import com.google.gson.Gson
import net.tlalka.android.fiszki.model.dto.json.LessonDto
import net.tlalka.android.fiszki.model.dto.json.WordDto
import net.tlalka.android.fiszki.model.types.LevelType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.io.InputStreamReader

class LessonDtoTest {

    @Test
    fun testParseJsonToLessonDto() {

        // given
        val stream = javaClass.getResourceAsStream("/samples/lesson.json")
        val reader = InputStreamReader(stream, "UTF-8")

        // when
        val dto = Gson().fromJson(reader, LessonDto::class.java)

        // then
        assertNotNull(dto)
        assertEquals(LevelType.BEGINNER, dto.level)
        assertEquals(3, dto.name.languages.size.toLong())
        assertEquals(2, dto.words.size.toLong())
    }

    @Test
    fun testParseLessonDtoToJson() {

        // given
        val dto = LessonDto()
        dto.name = WordDto()
        dto.level = LevelType.ADVANCED
        dto.words = listOf(WordDto(), WordDto())

        // when
        val result = Gson().toJson(dto)

        // then
        assertNotNull(result)
        assertFalse(result.isEmpty())
    }
}
