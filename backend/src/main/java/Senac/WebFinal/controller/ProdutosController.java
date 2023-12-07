package Senac.WebFinal.controller;

import Senac.WebFinal.model.Produto;
import Senac.WebFinal.repository.PedidosRepository;
import Senac.WebFinal.repository.ProdutosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/produtos")
public class ProdutosController {
    @Autowired
    private ProdutosRepository produtoRepository;

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
}
