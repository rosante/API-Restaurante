package com.ruzzante.apipedidos.application.web.resource.request

import com.ruzzante.apipedidos.domain.pedido.Pedido
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PedidoRequest(
    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val cliente: String,

    @field:NotNull
    val mesa: Boolean,

    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val endereco: String,

    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val pedido: String,

    //@field:NotNull
    //@field:NotBlank
    //@field:NotEmpty
    //val horario: String = LocalDateTime.now().toString(),

    val descricao: String,

    @field:NotNull
    val entregue: Boolean


){
    // Converte o Request para o Objeto do Domain
    fun toPedido() = Pedido(
        cliente = cliente,
        mesa = mesa,
        endereco = endereco,
        pedido = pedido,
        horario = LocalDateTime.now().format(DateTimeFormatter
            .ofPattern("dd/MM/uuuu hh:mm:ss")),
        descricao = descricao,
        entregue = entregue
    )

    companion object {
        fun to(id: Long, request: PedidoRequest) = Pedido(
            id = id,
            cliente = request.cliente,
            mesa = request.mesa,
            endereco = request.endereco,
            pedido = request.pedido,
            horario = LocalDateTime.now().format(DateTimeFormatter
                .ofPattern("dd/MM/uuuu hh:mm:ss")),
            descricao = request.descricao,
            entregue = request.entregue
        )
    }
}
