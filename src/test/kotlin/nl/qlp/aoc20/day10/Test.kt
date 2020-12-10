package nl.qlp.aoc20.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `2775 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(2775)
    }

    @Test
    fun `518344341716992 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(518344341716992)
    }
}
