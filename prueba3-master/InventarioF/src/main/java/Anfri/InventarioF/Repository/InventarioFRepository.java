package Anfri.InventarioF.Repository;
import Anfri.InventarioF.Model.InventarioF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioFRepository extends JpaRepository<InventarioF, Integer> {
}