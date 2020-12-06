package nl.qlp.aoc20.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `6763 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(6763)
    }

    @Test
    fun `3512 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(3512)
    }
}
