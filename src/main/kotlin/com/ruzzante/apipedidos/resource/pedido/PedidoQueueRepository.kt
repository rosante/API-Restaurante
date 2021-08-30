package com.ruzzante.apipedidos.resource.pedido

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*


class PedidoQueueRepository {

    companion object {
        val producer = PedidoQueueRepository().createProducer()

        fun produceMessages(producer:Producer<String, String>, topic: String, message: String) {
            val message = ProducerRecord(
                topic, // topic
                "Pedido", // key
                message // value
            )
            println("Producer sending message: $message")
            producer.send(message)

        }
    }

    private fun createProducer(): Producer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = "localhost:29092"
        props["acks"] = "all"
        props["retries"] = 0
        props["linger.ms"] = 1
        props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"

        return KafkaProducer(props)
    }


}