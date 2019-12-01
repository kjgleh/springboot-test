package me.kjgleh.springbootTest.service

import me.kjgleh.springbootTest.domain.Book
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class BookRestService(restTemplateBuilder: RestTemplateBuilder) {

    private var restTemplate: RestTemplate = restTemplateBuilder.rootUri("/rest/test").build()

    fun getRestBook(): Book {
        return restTemplate.getForObject("/rest/test", Book::class.java)!!
    }

}
