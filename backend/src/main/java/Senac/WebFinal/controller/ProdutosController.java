
package Senac.WebFinal.controller;

import Senac.WebFinal.model.Pedido;
import Senac.WebFinal.model.Produto;
import Senac.WebFinal.repository.PedidosRepository;
import Senac.WebFinal.repository.ProdutosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Origin"}, exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RequestMapping("/produtos")
public class ProdutosController {
    @Autowired
    private ProdutosRepository produtoRepository;
    @Autowired
    private PedidosRepository pedidoRepository;

    @GetMapping
    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Optional<Produto> buscarPorId(@RequestParam Integer id) {
        return produtoRepository.findById(id);
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Origin"}, exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public void efetuarCompra(@RequestParam int idProduto, @RequestParam int quantidade) {
        var produto = produtoRepository.findById(idProduto);
        var valorTotal = produto.get().getPreco() * quantidade;
        var pedido = new Pedido(quantidade, valorTotal, produto.get());
        pedidoRepository.save(pedido);
    }    
}
