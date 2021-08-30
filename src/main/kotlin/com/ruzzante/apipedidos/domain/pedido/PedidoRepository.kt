package com.ruzzante.apipedidos.domain.pedido

interface PedidoRepository {
    fun getDetail(id: Long): Pedido?
    fun getPedidos(): List<Pedido>
    fun getPedidosMesa(): List<Pedido>
    fun getPedidosEntrega(): List<Pedido>
    fun insert(pedido: Pedido):Pedido
    fun delete(id: Long)
    fun update(pedido: Pedido): Pedido
}