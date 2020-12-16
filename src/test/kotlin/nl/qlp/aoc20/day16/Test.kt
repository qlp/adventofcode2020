package nl.qlp.aoc20.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `23044 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(23044L)
    }

    @Test
    fun `3765150732757 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(3765150732757)
    }
}
