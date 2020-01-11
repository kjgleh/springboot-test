package me.kjgleh.springbootTest.repository

import me.kjgleh.springbootTest.domain.Book
import me.kjgleh.springbootTest.domain.Publisher
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

    @Autowired
    private lateinit var publisherRepository: PublisherRepository

    @Test
    fun test_save() {
        val title = UUID.randomUUID().toString()
        val publisher = Publisher(
            name = UUID.randomUUID().toString()
        )
        publisherRepository.save(publisher)
        val book = Book(
                title = title,
                publisher = publisher
        )
        testEntityManager.persist(book)

        val getBook = bookRepository.getOne(book.id)

        assertThat(getBook.title).isEqualTo(title)
    }
}