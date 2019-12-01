package me.kjgleh.springbootTest.controller

import me.kjgleh.springbootTest.domain.Book
import me.kjgleh.springbootTest.service.BookRestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookRestController {

    @Autowired
    private lateinit var bookRestService: BookRestService

    @GetMapping("/rest/test", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRestBook(): Book {
        return bookRestService.getRestBook()
    }
}