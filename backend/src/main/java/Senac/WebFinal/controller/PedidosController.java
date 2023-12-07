package Senac.WebFinal.controller;

import Senac.WebFinal.model.Pedido;
import Senac.WebFinal.repository.PedidosRepository;
import Senac.WebFinal.repository.ProdutosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/pedidos")
public class PedidosController {
    @Autowired
    private ProdutosRepository produtoRepository;
    @Autowired
    private PedidosRepository pedidoRepository;

    @GetMapping
    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }
    
    @PostMapping
    @CrossOrigin
    public void efetuarCompra(@RequestParam String nomeCompleto, @RequestParam String formaPagamento, @RequestParam int idProduto, @RequestParam int quantidade) {
        var produto = produtoRepository.findById(idProduto);
        var valorTotal = produto.get().getPreco() * quantidade;
        var pedido = new Pedido(nomeCompleto, formaPagamento, quantidade, valorTotal, produto.get());
        pedidoRepository.save(pedido);
    }    
}
