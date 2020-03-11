package org.board.api.domain.posts

import javax.persistence.*

@Entity
class Posts() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(length = 500, nullable = false)
    var title: String? = null

    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String? = null

    var author: String? = null

    constructor(builder: Builder): this() {
        this.title = builder.title
        this.content = builder.content
        this.author = builder.author
    }

    object Builder {
        var title: String? = null
            private set
        var content: String? = null
            private set
        var author: String? = null
            private set

        fun title(title: String?) = apply { this.title = title }
        fun content(content: String?) = apply { this.content = content }
        fun author(author: String?) = apply { this.author = author }
        fun build() = Posts(this)
    }
}

