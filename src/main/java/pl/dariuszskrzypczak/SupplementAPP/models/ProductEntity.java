package pl.dariuszskrzypczak.SupplementAPP.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @JoinColumn(name = "admin_id")
    @ManyToOne //wiele komentarzy do jednego usera = jeden komentarz ma jednego usera
    private AdminEntity admin;
}
