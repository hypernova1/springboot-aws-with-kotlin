package org.board.api.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
    }
}