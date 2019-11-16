package me.kjgleh.springbootTest.controller

import me.kjgleh.springbootTest.domain.Book
import me.kjgleh.springbootTest.service.BookService
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.util.*

@WebMvcTest(BookController::class)
internal class BookControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var bookService: BookService

    @Test
    fun `getBook returns book correctly`() {
        // Arrange
        val book = Book(
                id = Random().nextLong(),
                title = UUID.randomUUID().toString(),
                publishedAt = LocalDateTime.now()
        )

        given(bookService.getBook(book.id)).willReturn(Optional.of(book))

        // Act
        val actual = mockMvc.perform(get("/books/${book.id}"))

        // Assert
        actual.andExpect(status().isOk)
        actual.andExpect(jsonPath("title").value(book.title))
    }

    @Test
    fun `given wrong id then getBook returns HttpStatus NO_CONTENT`() {
        // Arrange
        val id = Random().nextLong()
        given(bookService.getBook(id)).willReturn(Optional.empty())

        // Act
        val actual = mockMvc.perform(get("/books/$id"))

        // Assert
        actual.andExpect(status().isNoContent)
    }

    @Test
    fun `getBookList returns book list correctly`() {
        // Arrange
        val bookList = (1..20).map {
            Book(
                    title = UUID.randomUUID().toString(),
                    publishedAt = LocalDateTime.now()
            )
        }
        given(bookService.getBookList()).willReturn(bookList)

        // Act
        val actual = mockMvc.perform(get("/books"))

        // Assert
        actual.andExpect(status().isOk)
        actual.andExpect(jsonPath("$", hasSize<Int>(20)))
    }
}