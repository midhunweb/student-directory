package com.midhun;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;


class ChoicePanelTest {

    @Test @DisplayName("NameTest")
    void testChoiceDataOne() {
        String input = "Madhu";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ChoicePanel c = new ChoicePanel();
        assertTrue(c.choiceData("1").equals("name"));


    }
    @Test @DisplayName("Admission Number")
    void testChoiceDataTwo() {
        String it = "1000";
        InputStream i = new ByteArrayInputStream(it.getBytes());
        System.setIn(i);
        ChoicePanel c = new ChoicePanel();
        assertEquals("admno",c.choiceData("2"));

    }
    @Test @DisplayName("Exit")
    void testChoiceDataThree() {

        ChoicePanel c = new ChoicePanel();
        assertTrue(c.choiceData("3").equals("exit"));

    }

    @Test @DisplayName("Invalid")
    void testChoiceDataOther() {

        ChoicePanel c = new ChoicePanel();
        assertTrue(c.choiceData("ug").equals("invalid"));

    }
}