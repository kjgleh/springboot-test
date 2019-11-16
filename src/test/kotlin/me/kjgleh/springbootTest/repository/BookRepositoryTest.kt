package me.kjgleh.springbootTest.repository

import me.kjgleh.springbootTest.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime
import java.util.*

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Test
    fun `save correctly`() {
        // Arrange
        val book = Book(
                title = UUID.randomUUID().toString(),
                publishedAt = LocalDateTime.now()
        )

        val sut = bookRepository

        // Act
        sut.save(book)
        val actual = sut.findById(book.id)

        // Assert
        assertThat(actual).isNotNull
        assertThat(actual.get().id).isEqualTo(book.id)
    }

    @Test
    fun `findAll correctly`() {
        // Arrange
        val bookList = (1..4).map {
            Book(
                    title = UUID.randomUUID().toString(),
                    publishedAt = LocalDateTime.now()
            )
        }
        val sut = bookRepository

        // Act
        sut.saveAll(bookList)
        val actual = sut.findAll()

        // Assert
        assertThat(actual).isNotNull
        assertThat(actual.size).isEqualTo(4)
        assertThat(actual).containsAll(bookList)
    }

    @Test
    fun `delete correctly`() {
        // Arrange
        val book = Book(
                title = UUID.randomUUID().toString(),
                publishedAt = LocalDateTime.now()
        )

        val sut = bookRepository

        // Act
        sut.save(book)
        sut.delete(book)
        val actual = sut.findById(book.id)

        // Assert
        assertThat(actual).isNotNull
        assertThat(actual.isPresent).isFalse()
    }
}