package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.Tags.ABUSE;
import static org.example.Tags.CLEAN;
import static org.example.Tags.ETC;
import static org.example.Tags.GENDER;
import static org.example.Tags.LOCALE;
import static org.example.Tags.RACE_COUNTRY;
import static org.example.Tags.RELIGION;
import static org.example.Tags.YEAR;
import static org.junit.jupiter.api.Assertions.*;

class TagsTest {

    @Test
    @DisplayName("각 태그의 열 번호가 적절히 나오는지")
    void testTagColumn() {
        // given
        int row = 1;

        // when
        Tags gender = GENDER;
        Tags raceCountry = RACE_COUNTRY;
        Tags year = YEAR;
        Tags locale = LOCALE;
        Tags religion = RELIGION;
        Tags abuse = ABUSE;
        Tags etc = ETC;
        Tags clean = CLEAN;

        // then
        assertEquals(gender.getTsvPosition(row), "B1");
        assertEquals(raceCountry.getTsvPosition(row), "C1");
        assertEquals(year.getTsvPosition(row), "D1");
        assertEquals(locale.getTsvPosition(row), "E1");
        assertEquals(religion.getTsvPosition(row), "F1");
        assertEquals(abuse.getTsvPosition(row), "G1");
        assertEquals(etc.getTsvPosition(row), "H1");
        assertEquals(clean.getTsvPosition(row), "I1");
    }
}