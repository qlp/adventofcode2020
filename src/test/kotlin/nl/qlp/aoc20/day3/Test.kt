package nl.qlp.aoc20.day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `454 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(259)
    }

    @Test
    fun `649 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(2224913600L)
    }
}