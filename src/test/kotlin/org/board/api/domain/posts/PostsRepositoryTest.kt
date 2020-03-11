package org.board.api.domain.posts

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostsRepositoryTest(@Autowired val postsRepository: PostsRepository) {

    @AfterEach
    fun cleanup() = postsRepository.deleteAll()

    @Test
    fun savePosts() {
        postsRepository.save(Posts.Builder
                .title("title")
                .content("content")
                .author("author")
                .build())

        val postsList = postsRepository.findAll()

        val posts = postsList[0]
        assertThat(posts.title).isEqualTo("title")
        assertThat(posts.content).isEqualTo("content")
        assertThat(posts.author).isEqualTo("author")

    }


}