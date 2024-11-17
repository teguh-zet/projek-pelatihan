package rifki.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rifki.project.models.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, Integer> {

}
