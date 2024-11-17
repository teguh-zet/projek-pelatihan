package rifki.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rifki.project.models.LoginAdmin;

public interface LoginAdminRepository extends JpaRepository<LoginAdmin,Integer> {
    
}
