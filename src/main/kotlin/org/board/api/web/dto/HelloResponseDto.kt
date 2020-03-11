package org.board.api.web.dto

class HelloResponseDto() {
    var name: String = ""
    var amount: Int = 0

    constructor(name: String, amount: Int) : this() {
        this.name = name
        this.amount = amount
    }
}
