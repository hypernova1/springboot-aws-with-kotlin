package org.board.api.domain.posts

import org.springframework.data.jpa.repository.JpaRepository

interface PostsRepository : JpaRepository<Posts, Long> {
    fun findByTitle(title: String)
}