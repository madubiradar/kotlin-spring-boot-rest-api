package com.kotlin.springboot.dto

import jakarta.persistence.*

@Entity
@Table(name ="courses")
data class Course(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    var name: String?,
    val category: String?
)