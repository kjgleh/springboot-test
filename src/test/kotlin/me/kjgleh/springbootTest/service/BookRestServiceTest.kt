package me.kjgleh.springbootTest.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withServerError
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.HttpServerErrorException

@RestClientTest(BookRestService::class)
internal class BookRestServiceTest {

    @Autowired
    private lateinit var bookRestService: BookRestService

    @Autowired
    private lateinit var mockRestServiceServer: MockRestServiceServer

    @Test
    fun `rest test`() {
        // Arrange
        val expect = """
            {"idx":null,"title":"테스트","publishedAt":"2019-12-12T12:00:00"}
        """
        mockRestServiceServer.expect(requestTo("/rest/test"))
                .andRespond(
                        withSuccess(
                                expect,
                                MediaType.APPLICATION_JSON
                        )
                )

        // Act
        val book = bookRestService.getRestBook()

        // Assert
        assertThat(book.title).isEqualTo("테스트")
    }

    @Test
    fun `rest error test`() {
        // Arrange
        val sut = bookRestService
        mockRestServiceServer.expect(requestTo("/rest/test"))
                .andRespond(withServerError())

        // Act & Assert
        assertThrows(HttpServerErrorException::class.java) {
            sut.getRestBook()
        }
    }
}