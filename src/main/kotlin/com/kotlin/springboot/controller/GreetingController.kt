package com.kotlin.springboot.controller

import com.kotlin.springboot.service.GreetingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greetings")
class GreetingController @Autowired constructor(val greetingService: GreetingService) {

    @GetMapping(path = ["/{name}"])
    fun sayGreetings(@PathVariable("name") name: String): String {
        return greetingService.save(name)
    }
}