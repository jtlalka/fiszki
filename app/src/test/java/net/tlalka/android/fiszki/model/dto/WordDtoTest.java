package net.tlalka.android.fiszki.model.dto;

import com.google.gson.Gson;
import net.tlalka.android.fiszki.model.dto.json.WordDto;
import net.tlalka.android.fiszki.model.types.LanguageType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class WordDtoTest {

    private Gson gson;

    @Before
    public void setup() {
        this.gson = new Gson();
    }

    @Test
    public void testParseJsonToWordDto() throws IOException {

        // given
        InputStream stream = getClass().getResourceAsStream("/samples/word.json");
        Reader reader = new InputStreamReader(stream, "UTF-8");

        // when
        WordDto dto = gson.fromJson(reader, WordDto.class);

        // then
        assertNotNull(dto);
        assertNotNull(dto.get(LanguageType.EN));
        assertNotNull(dto.get(LanguageType.PL));
        assertNotNull(dto.get(LanguageType.FR));
    }

    @Test
    public void testParseWordDtoToJson() throws IOException {

        // given
        WordDto dto = new WordDto();
        dto.put(LanguageType.EN, "dog");
        dto.put(LanguageType.PL, "pies");

        // when
        String result = gson.toJson(dto);

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
