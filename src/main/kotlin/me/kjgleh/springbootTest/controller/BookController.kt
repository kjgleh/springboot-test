package me.kjgleh.springbootTest.controller

import me.kjgleh.springbootTest.domain.Book
import me.kjgleh.springbootTest.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
class BookController(
        val bookService: BookService
) {

    @GetMapping("/books/{id}")
    fun getBook(@PathVariable id: Long): ResponseEntity<Book> {
        val optionalBook = bookService.getBook(id)
        return try {
            if (optionalBook.isPresent) {
                ResponseEntity.status(HttpStatus.OK).body(optionalBook.get())
            } else {
                ResponseEntity(HttpStatus.NO_CONTENT)
            }
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/books")
    fun getBookList(): List<Book> {
        return bookService.getBookList()
    }
}