package nl.qlp.aoc20.day9

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `1639024365 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(1639024365)
    }

    @Test
    fun `219202240 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(219202240)
    }
}
