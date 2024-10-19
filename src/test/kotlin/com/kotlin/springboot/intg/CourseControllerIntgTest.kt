package com.kotlin.springboot.intg

import com.kotlin.springboot.dto.Course
import com.kotlin.springboot.dto.CourseDto
import com.kotlin.springboot.repository.CourseRepository
import com.kotlin.springboot.util.courseEntityList
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerIntgTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setup() {
        courseRepository.deleteAll()
        val courses = courseEntityList()
        courseRepository.saveAll(courses)
    }

    @Test
    fun addCourse() {
        val courseDto = CourseDto(null, "build resful api", "Framework")

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

    @Test
    fun `test GET all courses`() {

        val courses = webTestClient.get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDto::class.java)
            .returnResult()
            .responseBody

        assertNotNull(courses)
        assertEquals(2, courses.size)
    }

    @Test
    fun `test PUT end point`(){
        //existing course
        val course =  Course(null, "Build RestFul", "Development")
        courseRepository.save(course)
        //update course

        val updatedCourseDto = CourseDto(null, "BUILD spring boot app", "dev")

        webTestClient.put()
            .uri("/v1/courses/{course_id}", course.id)
            .bodyValue(updatedCourseDto)
            .exchange()
            .expectStatus().isAccepted
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody


        //validate
        assertEquals("BUILD spring boot app", updatedCourseDto.name)

    }

    @Test
    fun `test DELETE end point`(){
        //existing course
        val course =  Course(null, "Build RestFul", "Development")
        courseRepository.save(course)
        //update course

        val updatedCourseDto = CourseDto(null, "BUILD spring boot app", "dev")

        webTestClient.delete()
            .uri("/v1/courses/{course_id}", course.id)
            .exchange()
            .expectStatus().isNoContent


        //validate
        assertEquals("BUILD spring boot app", updatedCourseDto.name)

    }


}