package com.growingtree.growingtreeserver.controller

import com.growingtree.growingtreeserver.service.TestService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
class TestController {
    private val testService = TestService()

    @GetMapping("")
    fun getTest(): String {
        return testService.getTest()
    }
}
