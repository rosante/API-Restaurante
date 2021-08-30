package com.ruzzante.restaurante.index

import com.ruzzante.restaurante.model.Pedido
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView
import javax.swing.text.View

@Controller
class IndexController {

    @RequestMapping("/")
    fun index() = RedirectView("/pedido")

}