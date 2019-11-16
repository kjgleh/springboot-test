package me.kjgleh.springbootTest.service

import me.kjgleh.springbootTest.domain.Book
import java.util.*

interface BookService {
    fun getBook(id: Long): Optional<Book>
    fun getBookList(): List<Book>
}
