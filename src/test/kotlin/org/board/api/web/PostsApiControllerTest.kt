package org.board.api.web

import org.assertj.core.api.Assertions.assertThat
import org.board.api.domain.posts.PostsRepository
import org.board.api.web.dto.PostsDto
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PostsApiControllerTest(
        @LocalServerPort val port: Int,
        @Autowired val restTemplate: TestRestTemplate,
        @Autowired val postsRepository: PostsRepository
) {
    @AfterEach
    fun tearDown() = postsRepository.deleteAll()

    @Test
    fun save() {
        val title = "title"
        val content = "content"

        val requestDto = PostsDto.SaveRequest.Builder
                .title(title)
                .content(content)
                .author("author")
                .build()

        val url = "http://localhost:${port}/api/v1/posts"

        val responseEntity = restTemplate
                .postForEntity(url, requestDto, Long::class.java)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isNotEqualTo(0L)
    }
}