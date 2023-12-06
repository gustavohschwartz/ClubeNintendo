package Senac.WebFinal.repository;

import Senac.WebFinal.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PedidosRepository extends JpaRepository<Pedido, Integer>{
    
}
