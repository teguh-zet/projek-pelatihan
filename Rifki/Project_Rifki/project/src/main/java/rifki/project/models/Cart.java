package rifki.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer qty;
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User id_user;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product id_product;
}
