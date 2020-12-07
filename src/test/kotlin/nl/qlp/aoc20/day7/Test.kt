package nl.qlp.aoc20.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun `121 is correct answer for First`() {
        assertThat(First().run()).isEqualTo(121)
    }
}
