package com.growingtree.growingtreeserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrowingTreeServerApplication

fun main(args: Array<String>) {
    runApplication<GrowingTreeServerApplication>(*args)
}
