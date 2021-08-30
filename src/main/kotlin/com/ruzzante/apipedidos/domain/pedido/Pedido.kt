package com.ruzzante.apipedidos.domain.pedido

class Pedido(
    val id: Long? = null,
    val cliente: String,
    val mesa: Boolean,
    val endereco: String,
    val pedido: String,
    val horario: String,
    var descricao: String,
    val entregue: Boolean
)