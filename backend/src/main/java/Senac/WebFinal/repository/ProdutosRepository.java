package Senac.WebFinal.repository;



import Senac.WebFinal.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProdutosRepository extends JpaRepository<Produto, Integer>{
    
    
}
