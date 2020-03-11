package org.board.api.service

import org.board.api.domain.posts.PostsRepository
import org.board.api.web.dto.PostsDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostsService(@Autowired private val postsRepository: PostsRepository) {

    fun save(requestDto: PostsDto.SaveRequest) = postsRepository.save(requestDto.toEntity()).id
}