package nl.qlp.aoc20.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `121 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(121)
    }

    @Test
    fun `3805 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(3805)
    }
}
