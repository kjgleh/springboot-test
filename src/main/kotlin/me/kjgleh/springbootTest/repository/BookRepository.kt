package me.kjgleh.springbootTest.repository

import me.kjgleh.springbootTest.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long>