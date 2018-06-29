package net.tlalka.fiszki.model.dto

import com.google.gson.Gson
import net.tlalka.fiszki.model.dto.json.WordDto
import net.tlalka.fiszki.model.types.LanguageType
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.io.InputStreamReader

class WordDtoTest {

    @Test
    fun testParseJsonToWordDto() {

        // given
        val stream = javaClass.getResourceAsStream("/samples/word.json")
        val reader = InputStreamReader(stream, "UTF-8")

        // when
        val dto = Gson().fromJson(reader, WordDto::class.java)

        // then
        assertNotNull(dto)
        assertNotNull(dto[LanguageType.EN])
        assertNotNull(dto[LanguageType.PL])
        assertNotNull(dto[LanguageType.FR])
    }

    @Test
    fun testParseWordDtoToJson() {

        // given
        val dto = WordDto()
        dto[LanguageType.EN] = "dog"
        dto[LanguageType.PL] = "pies"

        // when
        val result = Gson().toJson(dto)

        // then
        assertNotNull(result)
        assertFalse(result.isEmpty())
    }
}
