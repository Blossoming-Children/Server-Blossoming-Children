package com.growingtree.growingtreeserver.service

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class TestService {
    fun getTest() : String {
        return "Hello World"
    }
}