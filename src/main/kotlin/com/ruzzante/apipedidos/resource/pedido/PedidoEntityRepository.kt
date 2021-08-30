package com.ruzzante.apipedidos.resource.pedido

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PedidoEntityRepository : JpaRepository<PedidoEntity, Long>