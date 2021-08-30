package com.ruzzante.restaurante.pedido

import com.ruzzante.restaurante.model.Pedido
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class PedidoController{

    @RequestMapping("/pedido")
    fun pedido(model : Model){
        model.addAttribute("pedidos", Pedido.pedidos)
    }

}