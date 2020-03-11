package org.board.api.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class PostsDtoRepositoryTest(@Autowired val postsRepository: PostsRepository) {

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

    @Test
    fun baseTimeEntityTest() {
        val now = LocalDateTime.of(2020, 3, 11, 0, 0, 0)
        postsRepository.save(Posts.Builder
                .title("title")
                .content("content")
                .author("author")
                .build())

        val postsList = postsRepository.findAll()

        val posts = postsList[0]

        assertThat(posts.createdDate).isAfter(now)
        assertThat(posts.modifiedDate).isAfter(now)
    }

}