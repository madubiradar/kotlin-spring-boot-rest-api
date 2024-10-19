package com.kotlin.springboot.intg

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
class GreetingControllerIntgTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun `test greeting`(){
        val name = "Madu"

        val result = webTestClient.get()
            .uri("/v1/greetings/{name}", name)
            .exchange()
            .expectStatus().isOk
            .expectBody(String::class.java)
            .returnResult()

        assertEquals("Hello Madu", result.responseBody)
    }


}