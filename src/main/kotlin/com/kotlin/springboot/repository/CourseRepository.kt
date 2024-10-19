package com.kotlin.springboot.repository

import com.kotlin.springboot.dto.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository: JpaRepository<Course, Int>