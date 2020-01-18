package cdu.lincnaqiang.bookshop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne
    @JoinColumn(name="cid")
    private Category category;

    private String name;

    private String image;

    private float price;

    private float discount;

    private String author;

    private String publisher;

    private int stock;

    @Transient
    private int reviewCount;
}
