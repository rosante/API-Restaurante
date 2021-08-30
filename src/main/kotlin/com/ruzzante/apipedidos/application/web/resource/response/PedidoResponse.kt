package com.ruzzante.apipedidos.application.web.resource.response

import com.ruzzante.apipedidos.domain.pedido.Pedido

data class PedidoResponse (
    val id: Long?,
    val cliente: String,
    val mesa: Boolean,
    val endereco: String,
    val pedido: String,
    val horario: String,
    val descricao: String,
    val entregue: Boolean
){
    companion object {
        fun from(pedido: Pedido) = PedidoResponse(
            id = pedido.id,
            cliente = pedido.cliente,
            mesa = pedido.mesa,
            endereco = pedido.endereco,
            pedido = pedido.pedido,
            horario = pedido.horario,
            descricao = pedido.descricao,
            entregue = pedido.entregue
        )
    }
}
