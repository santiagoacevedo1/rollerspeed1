package com.rollerspeed.rollerspeed1.controller;

import com.rollerspeed.rollerspeed1.model.TipoFlor;
import com.rollerspeed.rollerspeed1.model.ArregloFloral;
import com.rollerspeed.rollerspeed1.model.Pedido;
import com.rollerspeed.rollerspeed1.service.TipoFlorService;
import com.rollerspeed.rollerspeed1.service.ArregloFloralService;
import com.rollerspeed.rollerspeed1.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/floristeria")
public class FloristeriaController {

    @Autowired
    private TipoFlorService tipoFlorService;

    @Autowired
    private ArregloFloralService arregloFloralService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String floristeria(Model model) {
        model.addAttribute("tiposFlor", tipoFlorService.listarTodos());
        model.addAttribute("arreglosFlorales", arregloFloralService.listarTodos());
        model.addAttribute("pedidos", pedidoService.listarTodos());
        return "floristeria";
    }

    // ==================== TIPO DE FLOR ====================

    @GetMapping("/tipo-flor")
    public String listarTipoFlor(Model model) {
        model.addAttribute("tiposFlor", tipoFlorService.listarTodos());
        return "tipo_flor";
    }

    @GetMapping("/tipo-flor/nuevo")
    public String nuevoTipoFlor(Model model) {
        model.addAttribute("tipoFlor", new TipoFlor());
        return "tipo_flor_form";
    }

    @GetMapping("/tipo-flor/editar/{id}")
    public String editarTipoFlor(@PathVariable Long id, Model model) {
        model.addAttribute("tipoFlor", tipoFlorService.obtenerPorId(id));
        return "tipo_flor_form";
    }

    @PostMapping("/tipo-flor/guardar")
    public String guardarTipoFlor(@ModelAttribute TipoFlor tipoFlor) {
        tipoFlorService.guardar(tipoFlor);
        return "redirect:/floristeria/tipo-flor";
    }

    @GetMapping("/tipo-flor/eliminar/{id}")
    public String eliminarTipoFlor(@PathVariable Long id) {
        tipoFlorService.eliminar(id);
        return "redirect:/floristeria/tipo-flor";
    }

    // ==================== ARREGLO FLORAL ====================

    @GetMapping("/arreglo-floral")
    public String listarArregloFloral(Model model) {
        model.addAttribute("arreglosFlorales", arregloFloralService.listarTodos());
        model.addAttribute("tiposFlor", tipoFlorService.listarTodos());
        return "arreglo_floral";
    }

    @GetMapping("/arreglo-floral/nuevo")
    public String nuevoArregloFloral(Model model) {
        model.addAttribute("arregloFloral", new ArregloFloral());
        model.addAttribute("tiposFlor", tipoFlorService.listarTodos());
        return "arreglo_floral_form";
    }

    @GetMapping("/arreglo-floral/editar/{id}")
    public String editarArregloFloral(@PathVariable Long id, Model model) {
        model.addAttribute("arregloFloral", arregloFloralService.obtenerPorId(id));
        model.addAttribute("tiposFlor", tipoFlorService.listarTodos());
        return "arreglo_floral_form";
    }

    @PostMapping("/arreglo-floral/guardar")
    public String guardarArregloFloral(@ModelAttribute ArregloFloral arregloFloral) {
        arregloFloralService.guardar(arregloFloral);
        return "redirect:/floristeria/arreglo-floral";
    }

    @GetMapping("/arreglo-floral/eliminar/{id}")
    public String eliminarArregloFloral(@PathVariable Long id) {
        arregloFloralService.eliminar(id);
        return "redirect:/floristeria/arreglo-floral";
    }

    // ==================== PEDIDO ====================

    @GetMapping("/pedido")
    public String listarPedido(Model model) {
        model.addAttribute("pedidos", pedidoService.listarTodos());
        model.addAttribute("arreglosFlorales", arregloFloralService.listarTodos());
        return "pedido";
    }

    @GetMapping("/pedido/nuevo")
    public String nuevoPedido(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("arreglosFlorales", arregloFloralService.listarTodos());
        return "pedido_form";
    }

    @GetMapping("/pedido/editar/{id}")
    public String editarPedido(@PathVariable Long id, Model model) {
        model.addAttribute("pedido", pedidoService.obtenerPorId(id));
        model.addAttribute("arreglosFlorales", arregloFloralService.listarTodos());
        return "pedido_form";
    }

    @PostMapping("/pedido/guardar")
    public String guardarPedido(@ModelAttribute Pedido pedido) {
        if (pedido.getFechaPedido() == null) {
            pedido.setFechaPedido(LocalDate.now());
        }
        if (pedido.getEstado() == null || pedido.getEstado().isEmpty()) {
            pedido.setEstado("Pendiente");
        }
        pedidoService.guardar(pedido);
        return "redirect:/floristeria/pedido";
    }

    @GetMapping("/pedido/eliminar/{id}")
    public String eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return "redirect:/floristeria/pedido";
    }
}
