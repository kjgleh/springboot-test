package me.kjgleh.springbootTest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
class SpringbootTestApplication

fun main(args: Array<String>) {
    runApplication<SpringbootTestApplication>(*args)
}
