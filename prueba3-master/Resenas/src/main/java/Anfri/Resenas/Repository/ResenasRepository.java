package Anfri.Resenas.Repository;

import Anfri.Resenas.Model.ResenasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenasRepository extends JpaRepository<ResenasModel, Integer> {
    List<ResenasModel> findByPedidoId(Integer pedidoId);
}