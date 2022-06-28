package com.midhun;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    @Test @DisplayName("Physics Grade")
    void phyGrade() {
        assertTrue(Grade.phyGrade(73).equals("B1"));
    }

    @Test @DisplayName("Chemistry Grade")
    void cheGrade() {
        assertTrue(Grade.cheGrade(56).equals("C1"));
    }

    @Test @DisplayName("Maths Grade")
    void mathGrade() {
        assertTrue(Grade.mathGrade(91).equals("A1"));
    }
}