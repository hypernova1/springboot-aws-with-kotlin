package org.board.api.web

import org.board.api.service.PostsService
import org.board.api.web.dto.PostsDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/posts")
class PostsApiController(@Autowired private val postsService: PostsService) {

    @PostMapping
    fun save(@RequestBody requestDto: PostsDto.SaveRequest) = postsService.save(requestDto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long,
               @RequestBody requestDto: PostsDto.UpdateRequest) = postsService.update(id, requestDto)

}