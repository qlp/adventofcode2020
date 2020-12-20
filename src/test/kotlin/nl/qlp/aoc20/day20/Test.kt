package nl.qlp.aoc20.day20

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `20033377297069 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(20033377297069)
    }

    @Test
    fun `2084 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(2084)
    }
}
