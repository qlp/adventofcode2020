package nl.qlp.aoc20.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `866 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(866)
    }

    @Test
    fun `583 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(583)
    }
}