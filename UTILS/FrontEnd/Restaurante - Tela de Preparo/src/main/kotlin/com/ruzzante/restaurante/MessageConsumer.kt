package com.ruzzante.restaurante

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ruzzante.restaurante.model.Pedido
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*

@Service
class MessageConsumer{

    companion object{
        val pedidos: MutableList<Pedido> = mutableListOf()
            get() = field
    }

    val mapper = jacksonObjectMapper()

    fun createConsumer(): Consumer<String, String> {
        val props = Properties()
        props.setProperty("bootstrap.servers", "localhost:29092, localhost:39092")
        props.setProperty("group.id", "grupo_tela")
        props.setProperty("auto.offset.reset", "earliest")
        props.setProperty("enable.auto.commit", "false")
        props.setProperty("auto.commit.interval.ms", "60000")
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        return KafkaConsumer(props)
    }

    fun consumeRecords(consumer: Consumer<String, String>, topic: String) {
        consumer.subscribe(listOf(topic))
        while (true) {
            val records: ConsumerRecords<String, String> = consumer.poll(Duration.ofSeconds(5))
            records.iterator().forEach {
                println("Recebido: ${it.value()}")
                val pedido: Pedido = mapper.readValue(it.value())
                Pedido.atualizaPedido(pedido)
            }

            consumer.commitAsync()
        }
    }



}