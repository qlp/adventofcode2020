package nl.qlp.aoc20.day23

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `30138 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(30138)
    }

    @Test
    fun `286194102744 is correct answer for Second`() {
        assertThat(Second().run()).isEqualTo(286194102744)
    }
}
