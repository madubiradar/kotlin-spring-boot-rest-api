package com.kotlin.springboot.exception

import org.aspectj.bridge.Message

class ErrorMessage(
    val message: String,
    val statusCode: Int
)