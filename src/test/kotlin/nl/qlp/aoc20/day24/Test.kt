package nl.qlp.aoc20.day24

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `427 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(427)
    }

    @Test
    fun `3837 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(3837)
    }
}
