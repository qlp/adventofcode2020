package nl.qlp.aoc20.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.System.currentTimeMillis

class Test {
    @Test
    fun `2020 with two numbers should return 864864`() {
        assertThat(First().run(2020)).isEqualTo(864864)
    }

    @Test
    fun `2020 with three numbers should return 281473080`() {
        assertThat(Second().run(2020)).isEqualTo(281473080)
    }

    @Test
    fun `2020 with two numbers should return 864864 (generic)`() {
        assertThat(Generic().run(2020, 2)).isEqualTo(864864)
    }

    @Test
    fun `2020 with three numbers should return 281473080 (generic)`() {
        assertThat(Generic().run(2020, 3)).isEqualTo(281473080)
    }

    @Test
    fun `Performance of Generic is comparable`() {
        // warm up
        repeat(100) {
            assertThat(Second().run(2020)).isEqualTo(281473080)
            assertThat(Generic().run(2020, 3)).isEqualTo(281473080)
        }

        val three = time {
            repeat(10) {
                assertThat(Second().run(2020))
            }
        }

        val generic = time {
            repeat(10) {
                assertThat(Generic().run(2020, 3))
            }
        }

        val marginInPercent = 10

        assertThat(three * (1 + marginInPercent.toDouble() / 100)).isGreaterThan(generic.toDouble())
    }

    private fun time(function: () -> Unit) : Long {
        val start = currentTimeMillis()

        function()

        return currentTimeMillis() - start
    }
}