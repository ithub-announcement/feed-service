package ru.itcollege.feedservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FeedServiceApplication

fun main(args: Array<String>) {
  runApplication<FeedServiceApplication>(*args)
}
