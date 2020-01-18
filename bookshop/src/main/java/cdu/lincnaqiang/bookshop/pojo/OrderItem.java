package cdu.lincnaqiang.bookshop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orderItem")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="pid")
    private Product product;

    @ManyToOne
    @JoinColumn(name="oid")
    private Order order;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    private int number;
}
