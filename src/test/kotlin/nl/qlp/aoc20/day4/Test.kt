package nl.qlp.aoc20.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `213 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(213)
    }

    @Test
    fun `147 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(147)
    }
}