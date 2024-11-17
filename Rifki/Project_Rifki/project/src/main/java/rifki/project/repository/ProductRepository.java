package rifki.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rifki.project.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
