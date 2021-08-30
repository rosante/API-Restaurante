package com.ruzzante.restaurante

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import kotlin.concurrent.thread

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
class RestauranteTelaDePreparoApplication

fun main(args: Array<String>) {
	runApplication<RestauranteTelaDePreparoApplication>(*args)

	val consumer = MessageConsumer().createConsumer()
	thread { MessageConsumer().consumeRecords(consumer, "lista_preparacao") }
}
