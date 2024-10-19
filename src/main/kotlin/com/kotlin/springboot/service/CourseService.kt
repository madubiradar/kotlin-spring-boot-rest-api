package com.kotlin.springboot.service

import com.kotlin.springboot.dto.Course
import com.kotlin.springboot.dto.CourseDto
import com.kotlin.springboot.exception.CourseNotFoundException
import com.kotlin.springboot.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseService @Autowired constructor(val courseRepository: CourseRepository){

    fun addCourse(courseDto: CourseDto) : CourseDto {
         // build course entity
        val courseEntity = courseDto.let {
            Course(null, it.name, it.category)
        }
        courseRepository.save(courseEntity)

        //build course dto
        return courseEntity.let {
            CourseDto(it.id, it.name, it.category)
        }

    }

    fun getCourses(): List<CourseDto> {
        return courseRepository.findAll().map {
            CourseDto(it.id, it.name, it.category)
        }

    }

    fun updateCourse(courseDto: CourseDto, courseId: Int): CourseDto {

        val existCourse = courseRepository.findById(courseId)

        return if(existCourse.isPresent){
            existCourse.get()
                .let {
                    it.name = courseDto.name
                    it.name = courseDto.category
                    courseRepository.save(it)
                    CourseDto (it.id, it.name, it.category)
                }
        } else {
                throw CourseNotFoundException("No course for given Id: $courseId")
        }
    }

    fun delete(courseId: Int) {

        val existCourse = courseRepository.findById(courseId)

         if(existCourse.isPresent){
            existCourse.get()
                .let {
                    courseRepository.deleteById(courseId)
                }
        } else {
            throw CourseNotFoundException("No course for given Id: $courseId")
        }
    }
}