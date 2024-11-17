package rifki.project.models;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LoginUser {
    @Id
    // @GeneratedValue(strategy = GenerationType.class)
    private Integer id;

    private String username;

    private String password;
}
