package me.kjgleh.springbootTest.repository

import me.kjgleh.springbootTest.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.util.*


@DataJpaTest
@ActiveProfiles("test2")
internal class BookRepositoryTest2 {

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Test
    fun test_save() {
        val title = UUID.randomUUID().toString()
        val book = Book(
                title = title,
                publishedAt = LocalDateTime.now()
        )
        testEntityManager.persist(book)

        val getBook = bookRepository.getOne(book.id)

        assertThat(getBook.title).isEqualTo(title)
    }
}