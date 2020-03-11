package org.board.api.web

import org.board.api.web.dto.HelloResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(): String = "hello"

    @GetMapping("/hello/dto")
    fun helloDto(@RequestParam name: String,
                 @RequestParam amount: Int): HelloResponseDto = HelloResponseDto(name, amount)
}