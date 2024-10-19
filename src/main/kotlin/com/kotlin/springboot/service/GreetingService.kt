package com.kotlin.springboot.service

import org.springframework.stereotype.Service

@Service
class GreetingService {

    fun save(name: String) = "Hello $name"
}