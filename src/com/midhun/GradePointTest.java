package com.midhun;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradePointTest {

    @Test @DisplayName("Physics Grade Point")
    void phyGradePoint() {
        assertTrue(GradePoint.phyGradePoint(85).equals("9.0"));
    }

    @Test @DisplayName("Chemistry Grade Point")
    void cheGradePoint() {
        assertTrue(GradePoint.cheGradePoint(15).equals("C"));
    }

    @Test  @DisplayName("Maths Grade Point")
    void mathGradePoint() {
        assertTrue(GradePoint.mathGradePoint(45).equals("5.0"));
    }
}