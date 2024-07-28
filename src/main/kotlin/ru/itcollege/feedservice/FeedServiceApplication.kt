package ru.itcollege.feedservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class FeedServiceApplication

fun main(args: Array<String>) {
  runApplication<FeedServiceApplication>(*args)
}
