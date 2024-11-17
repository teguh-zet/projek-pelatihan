package rifki.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rifki.project.models.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
