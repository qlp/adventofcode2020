package nl.qlp.aoc20.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `2095 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(2095)
    }

    @Test
    fun `598411311431841 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(598411311431841)
    }
}
