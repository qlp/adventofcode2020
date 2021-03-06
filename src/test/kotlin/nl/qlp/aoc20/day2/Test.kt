package nl.qlp.aoc20.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `454 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(454)
    }

    @Test
    fun `649 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(649)
    }
}