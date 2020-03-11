package org.board.api.web

import org.board.api.service.PostsService
import org.board.api.web.dto.PostsDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostsApiController(@Autowired private val postsService: PostsService) {

    @PostMapping("/api/v1/posts")
    fun save(@RequestBody requestDto: PostsDto.SaveRequest) = postsService.save(requestDto)

}