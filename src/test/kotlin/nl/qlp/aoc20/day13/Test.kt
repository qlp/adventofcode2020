package nl.qlp.aoc20.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `636 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(636)
    }

    @Test
    fun `26841 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(26841)
    }
}
