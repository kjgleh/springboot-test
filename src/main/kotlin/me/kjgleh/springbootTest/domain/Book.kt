package me.kjgleh.springbootTest.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "books")
@EntityListeners(AuditingEntityListener::class)
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    var title: String,
    @CreatedDate
    var publishedAt: LocalDateTime
)