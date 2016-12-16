package net.tlalka.android.fiszki.models.dto;

import com.google.gson.Gson;
import net.tlalka.android.fiszki.models.dto.json.LessonDto;
import net.tlalka.android.fiszki.models.dto.json.WordDto;
import net.tlalka.android.fiszki.models.types.LevelType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class LessonDtoTest {

    private Gson gson;

    @Before
    public void setup() {
        this.gson = new Gson();
    }

    @Test
    public void testParseJsonToLessonDto() throws IOException {

        // given
        InputStream stream = getClass().getResourceAsStream("/samples/lesson.json");
        Reader reader = new InputStreamReader(stream, "UTF-8");

        // when
        LessonDto dto = gson.fromJson(reader, LessonDto.class);

        // then
        assertNotNull(dto);
        assertEquals(LevelType.BEGINNER, dto.getLevel());
        assertEquals(3, dto.getName().getLanguages().size());
        assertEquals(2, dto.getWords().size());
    }

    @Test
    public void testParseLessonDtoToJson() throws IOException {

        // given
        LessonDto dto = new LessonDto();
        dto.setName(new WordDto());
        dto.setLevel(LevelType.ADVANCED);
        dto.setWords(Arrays.asList(new WordDto(), new WordDto()));

        // when
        String result = gson.toJson(dto);

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
