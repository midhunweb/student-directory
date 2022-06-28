package com.midhun;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtraTest {

    @Test @DisplayName("Fail or Pass check")
    void testFailCheck() {
        assertTrue(Extra.failCheck("Saz",1020,34,67,57).equals("Fail"));
        assertTrue(Extra.failCheck("Saz",1020,34,17,52).equals("Fail"));
        assertTrue(Extra.failCheck("Saz",1020,34,17,12).equals("Fail"));
        assertTrue(Extra.failCheck("Saz",1020,84,67,52).equals("Pass"));
    }
}