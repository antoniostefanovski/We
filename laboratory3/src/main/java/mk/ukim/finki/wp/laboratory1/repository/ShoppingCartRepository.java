package mk.ukim.finki.wp.laboratory1.repository;

import mk.ukim.finki.wp.laboratory1.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
    List<ShoppingCart> findByDateCreatedAfter(LocalDateTime from);
    List<ShoppingCart> findByDateCreatedBefore(LocalDateTime to);
}
