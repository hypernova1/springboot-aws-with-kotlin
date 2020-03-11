package org.board.api.service

import org.board.api.domain.posts.PostsRepository
import org.board.api.web.dto.PostsDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostsService(@Autowired private val postsRepository: PostsRepository) {

    fun save(requestDto: PostsDto.SaveRequest) = postsRepository.save(requestDto.toEntity()).id

    @Transactional
    fun update(id: Long, requestDto: PostsDto.UpdateRequest): Long {
        val posts = postsRepository.findById(id)
                .orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id=$id") }

        posts.update(requestDto.title, requestDto.content)

        return id
    }

    fun findById(id: Long): PostsDto.Response {
        val entity = postsRepository.findById(id)
                .orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id=$id") }

        return PostsDto.Response(entity)
    }

}