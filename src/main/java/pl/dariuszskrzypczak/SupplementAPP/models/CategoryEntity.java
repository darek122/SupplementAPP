package pl.dariuszskrzypczak.SupplementAPP.models;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;

    private String name;

    @OneToMany(mappedBy = "category",  cascade = CascadeType.ALL)
    private List<ProductEntity> products;



}
