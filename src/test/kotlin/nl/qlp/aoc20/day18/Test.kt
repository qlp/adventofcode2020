package nl.qlp.aoc20.day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `29839238838303L is correct answer for First`() {
        assertThat(First().run()).isEqualTo(29839238838303L)
    }

    @Test
    fun `201376568795521 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(201376568795521)
    }
}
