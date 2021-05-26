package com.globallogic.javaacademy.functional.homework;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HomeworkTest {

    @Test
    void shouldSelectNames() {
        // given
        final List<String> names = List.of("Anna", "Rita", "Ala", "Piotr", "Patryk", "Wojtek");

        // when
        final Map<String, Integer> result = Homework.selectNames(names);

        // then
        assertEquals(3, result.size());

        assertTrue(result.containsKey("RITA"));
        assertEquals(4, result.get("RITA"));

        assertTrue(result.containsKey("ANNA"));
        assertEquals(4, result.get("ANNA"));

        assertTrue(result.containsKey("PATRYK"));
        assertEquals(6, result.get("PATRYK"));
    }

}