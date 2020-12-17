package nl.qlp.aoc20.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `322 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(322)
    }

    @Test
    fun `3765150732757 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(3765150732757)
    }
}
