package com.kotlin.springboot.controller

import com.kotlin.springboot.dto.CourseDto
import com.kotlin.springboot.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/courses")
class CourseController @Autowired constructor(val courseService: CourseService){

    //companion object: KLogging();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourses(@RequestBody courseDto: CourseDto): CourseDto{
        return courseService.addCourse(courseDto)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCourses(): List<CourseDto> {
        return courseService.getCourses()
    }

    @PutMapping("/{course_id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateCourse(@RequestBody courseDto: CourseDto, @PathVariable("course_id") courseId: Int): CourseDto {
        return courseService.updateCourse(courseDto,courseId)
    }

    @DeleteMapping("/{course_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable("course_id") courseId: Int) {
        return courseService.delete(courseId)
    }
}