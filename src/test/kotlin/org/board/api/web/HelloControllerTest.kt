package org.board.api.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.hamcrest.Matchers.`is`

@WebMvcTest
class HelloControllerTest(
        @Autowired
        private val mockMvc: MockMvc
) {
    @Test
    fun hello() {
        val hello = "hello"
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk)
                .andExpect(content().string(hello))
                .andDo(print())
    }

    @Test
    fun helloDto() {
        val name = "hello"
        val amount = 1000

        mockMvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", amount.toString()))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name", `is`(name)))

    }
}