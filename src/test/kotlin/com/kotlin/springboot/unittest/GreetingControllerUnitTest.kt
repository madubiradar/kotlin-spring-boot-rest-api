package com.kotlin.springboot.unittest

import com.kotlin.springboot.controller.GreetingController
import com.kotlin.springboot.service.GreetingService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertEquals

@WebMvcTest(controllers = [GreetingController::class])
@AutoConfigureWebClient
class GreetingControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var greetingServiceMockk: GreetingService

    @Test
    fun `test greeting`(){
        val name = "Madu"

        every {
            greetingServiceMockk.save(any())
        } returns
            "Hello Madu"

        val result = webTestClient.get()
            .uri("/v1/greetings/{name}", name)
            .exchange()
            .expectStatus().isOk
            .expectBody(String::class.java)
            .returnResult()

        assertEquals("Hello Madu", result.responseBody)
    }

}