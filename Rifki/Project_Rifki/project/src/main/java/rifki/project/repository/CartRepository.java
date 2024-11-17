package rifki.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rifki.project.models.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    
}
