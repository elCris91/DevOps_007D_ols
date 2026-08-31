package Anfri.InventarioV.Repository;

import Anfri.InventarioV.Model.InventarioV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioVRepository extends JpaRepository<InventarioV, Integer> {
}