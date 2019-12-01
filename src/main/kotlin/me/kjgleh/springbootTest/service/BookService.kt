package me.kjgleh.springbootTest.service

import me.kjgleh.springbootTest.domain.Book
import org.springframework.stereotype.Service
import java.util.*

@Service
interface BookService {
    fun getBook(id: Long): Optional<Book>
    fun getBookList(): List<Book>
}
