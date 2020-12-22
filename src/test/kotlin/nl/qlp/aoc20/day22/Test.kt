package nl.qlp.aoc20.day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `2635 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(2635)
    }

    @Test
    fun `0 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(0)
    }
}
