package nl.qlp.aoc20.day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `17934269678453 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(17934269678453L)
    }

    @Test
    fun `3440662844064 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(3440662844064)
    }
}
