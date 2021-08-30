package com.ruzzante.apipedidos.resource.pedido

import com.ruzzante.apipedidos.domain.pedido.Pedido
import com.ruzzante.apipedidos.domain.pedido.PedidoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PedidoRepositoryImpl(
    @Autowired private val repository: PedidoEntityRepository
) : PedidoRepository {
    override fun getDetail(id: Long): Pedido? =
        repository.findByIdOrNull(id)?.toPedido()

    override fun getPedidos(): List<Pedido> =
        repository.findAll().map { it.toPedido() }

    override fun getPedidosMesa(): List<Pedido> =
        repository.findAll()
            .filter { pedido -> pedido.mesa }
            .map { it.toPedido() }

    override fun getPedidosEntrega(): List<Pedido> =
        repository.findAll()
            .filter { pedido -> !pedido.mesa }
            .map { it.toPedido() }

    override fun insert(Pedido: Pedido): Pedido =
        repository.save(PedidoEntity.from(Pedido)).toPedido()

    override fun delete(id: Long) = repository.deleteById(id)

    override fun update(Pedido: Pedido): Pedido =
        repository.save(PedidoEntity.from(Pedido)).toPedido()
}