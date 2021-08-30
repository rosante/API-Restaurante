package com.ruzzante.apipedidos.resource.pedido

import com.ruzzante.apipedidos.domain.pedido.Pedido
import javax.persistence.*

@Entity
@Table(name="pedido")
data class PedidoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(nullable =false)
    val cliente: String,
    @Column(nullable =false)
    val mesa: Boolean,
    @Column(nullable =false)
    val endereco: String,
    @Column(nullable =false)
    val pedido: String,
    @Column(nullable =false)
    val horario: String,
    val descricao: String,
    @Column(nullable =false)
    val entregue: Boolean
){
    fun toPedido() = Pedido(
        id = id,
        cliente = cliente,
        mesa = mesa,
        endereco = endereco,
        pedido = pedido,
        horario = horario,
        descricao = descricao,
        entregue = entregue
    )
    companion object{
        fun from(pedido: Pedido) = PedidoEntity(
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
