package com.ruzzante.apipedidos.application.web.resource

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ruzzante.apipedidos.application.web.resource.request.PedidoRequest
import com.ruzzante.apipedidos.application.web.resource.response.PedidoResponse
import com.ruzzante.apipedidos.domain.pedido.PedidoRepository
import com.ruzzante.apipedidos.resource.pedido.PedidoQueueRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.Valid

private const val API_PATH = "/pedido"
private val producer = PedidoQueueRepository
private val mapper = jacksonObjectMapper()

@RestController
@RequestMapping(value = [API_PATH])
class PedidoResource (
    @Autowired private val repository: PedidoRepository
){

    @GetMapping
    fun getPedidos() = repository.getPedidos()
        .map{PedidoResponse.from(it)}
        .let{ ResponseEntity.ok().body(it) }

    @GetMapping("/PedidosMesa")
    fun getPedidosMesa() = repository.getPedidosMesa()
        .map{PedidoResponse.from(it)}
        .let{ ResponseEntity.ok().body(it) }

    @GetMapping("/PedidosEntrega")
    fun getPedidosEntrega() = repository.getPedidosEntrega()
        .map{PedidoResponse.from(it)}
        .let{ ResponseEntity.ok().body(it) }

    @GetMapping("{id}")
    fun getPedidoDetails(@PathVariable("id") id:Long) = repository.getDetail(id)
        ?.let{ResponseEntity.ok().body(PedidoResponse.from(it))}
        ?: ResponseEntity.notFound().build<Void>()

    @PostMapping
    fun insertPedido(@Valid @RequestBody request: PedidoRequest) = request.toPedido()
        .run {
            repository.insert(this)
        }
        .let{
            producer.produceMessages(producer.producer, "lista_preparacao", mapper.writeValueAsString(it))
            ResponseEntity
                .created(URI("$API_PATH/${it.id}"))
                .body(PedidoResponse.from(it))
        }

    @PutMapping("{id}")
    fun updatePedido(@Valid @RequestBody request: PedidoRequest, @PathVariable("id") id:Long) =
        repository.getDetail(id)?.let {
            PedidoRequest.to(it.id!!, request)
                .apply {
                    repository.update(this)
                }
                .let{ pedido ->
                    var pedidoDescricao = pedido
                    pedidoDescricao.descricao += " - Alterado em ${LocalDateTime.now()
                        .format(DateTimeFormatter
                        .ofPattern("dd/MM/uuuu hh:mm:ss"))}"
                    producer.produceMessages(producer.producer, "lista_preparacao", mapper.writeValueAsString(pedido))
                    ResponseEntity.accepted().body(PedidoResponse.from(pedido))
                    //ResponseEntity.ok().body(PedidoResponse.from(pedido))
                }
        } ?: ResponseEntity.notFound().build<Void>()

    @DeleteMapping("{id}")
    fun deletePedido(@PathVariable("id") id:Long) = repository.delete(id)
        ?.let{ResponseEntity.accepted().build<Void>()}
        ?: ResponseEntity.notFound().build<Void>()
}