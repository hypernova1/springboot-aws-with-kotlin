package org.board.api.web.dto

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

internal class HelloResponseDtoTest {

    @Test
    fun dtoTest() {
        val name = "test"
        val amount = 1000

        val dto = HelloResponseDto()
        dto.name = "test"
        dto.amount = 1000

        assertThat(dto.name).isEqualTo(name)
        assertThat(dto.amount).isEqualTo(amount)
    }
}