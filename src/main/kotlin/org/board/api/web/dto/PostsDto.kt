package org.board.api.web.dto

import org.board.api.domain.posts.Posts

class PostsDto {

    class SaveRequest() {
        var title: String? = null
            private set
        var content: String? = null
            private set
        var author: String? = null
            private set

        constructor(builder: Builder): this() {
            this.title = builder.title
            this.content = builder.content
            this.author = builder.author
        }

        fun toEntity() = Posts.Builder
                .title(title)
                .content(content)
                .author(author)
                .build()

        object Builder {

            var title: String? = null
                private set
            var content: String? = null
                private set
            var author: String? = null
                private set

            fun title(title: String) = apply { this.title = title }
            fun content(content: String) = apply { this.content = content }
            fun author(author: String) = apply { this.author = author }
            fun build() = SaveRequest(this)
        }
    }

}