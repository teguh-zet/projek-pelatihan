package rifki.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LoginAdmin {
    @Id
    private Integer id;
    private String username;
    private String password;
}
