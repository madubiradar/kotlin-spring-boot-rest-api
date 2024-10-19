package com.kotlin.springboot.unittest

import com.kotlin.springboot.controller.CourseController
import com.kotlin.springboot.dto.CourseDto
import com.kotlin.springboot.service.CourseService
import com.kotlin.springboot.util.courseDTO
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertNotNull

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebClient
class CourseControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var courseService: CourseService

    @Test
    fun addCourse() {


        val courseDto = CourseDto(null, "build resful api", "Framework")

        every {
            courseService.addCourse(courseDto)
        } returns courseDTO(id = 1)

        val savedDto = webTestClient.post()
            .uri("/v1/courses")
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody

        if (savedDto != null) {
            assertNotNull(savedDto.id!!)
        }
    }

}