package me.kjgleh.springbootTest.repository

import me.kjgleh.springbootTest.domain.Publisher
import org.springframework.data.jpa.repository.JpaRepository

interface PublisherRepository: JpaRepository<Publisher, Int>