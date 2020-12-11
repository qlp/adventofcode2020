package nl.qlp.aoc20.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `2243 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(2243)
    }

    @Test
    fun `2027 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(2027)
    }
}
