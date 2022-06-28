package com.midhun;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalPercTest {

    @Test @DisplayName("Total Marks check")
    void testTotal() {
        assertTrue(TotalPerc.total(30,40,50) == 120);
    }

    @Test @DisplayName("Percentage Mark check")
    void testPerc() {
        assertTrue(TotalPerc.perc(150) == 50);
    }
}