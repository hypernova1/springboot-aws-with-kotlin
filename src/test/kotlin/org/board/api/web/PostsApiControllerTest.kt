package org.board.api.web

import org.assertj.core.api.Assertions.assertThat
import org.board.api.domain.posts.Posts
import org.board.api.domain.posts.PostsRepository
import org.board.api.web.dto.PostsDto
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
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
        assertThat(responseEntity.body).isGreaterThan(0L)
    }

    @Test
    fun update() {
        val savedPosts = postsRepository.save(Posts.Builder
                .title("title")
                .content("content")
                .author("author")
                .build())

        val updateId = savedPosts.id
        val expectedTitle = "title2"
        val expectedContent = "content2"

        val requestDto = PostsDto.UpdateRequest(expectedTitle, expectedContent)

        val url = "http://localhost:${port}/api/v1/posts/$updateId"

        val requestEntity = HttpEntity<PostsDto.UpdateRequest>(requestDto)

        val responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long::class.java)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }
}