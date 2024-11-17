package rifki.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rifki.project.models.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer>{
    
}
