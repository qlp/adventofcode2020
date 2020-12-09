package nl.qlp.aoc20.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `1528 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(1528)
    }

    @Test
    fun `640 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(640)
    }
}
