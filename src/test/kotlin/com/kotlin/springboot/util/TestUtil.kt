package com.kotlin.springboot.util

import com.kotlin.springboot.dto.Course
import com.kotlin.springboot.dto.CourseDto


fun courseEntityList() = listOf(
    Course(null, "Build RestFul", "Development"),
    Course(null, "Spring boot", "Framework")
)

fun courseDTO(
     id: Int? =null,
     name: String = "Build REST API using spring boot",
     category: String =" Madu Biradar",
) = CourseDto(
    id,
    name,
    category
)
