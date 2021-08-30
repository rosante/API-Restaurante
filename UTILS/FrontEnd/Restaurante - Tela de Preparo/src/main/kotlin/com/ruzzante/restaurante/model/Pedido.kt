package com.ruzzante.restaurante.model

import com.ruzzante.restaurante.MessageConsumer


class Pedido (
    val id: Long,
    val cliente: String,
    val pedido: String,
    val mesa: Boolean,
    val endereco: String,
    val horario: String,
    val descricao: String,
    val entregue: Boolean
    ){
    companion object{
        val pedidos: MutableList<Pedido> = mutableListOf()
            get() = field

        fun atualizaPedido(pedido: Pedido){
            when {
                pedido.entregue -> pedidos.removeIf { p -> p.id == pedido.id }
                pedidos.any { p -> p.id == pedido.id } -> {
                    pedidos.removeIf { p -> p.id == pedido.id }
                    pedidos.add(pedido)
                }
                else -> pedidos.add(pedido)
            }

        }
    }

}