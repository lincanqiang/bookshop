package cdu.lincnaqiang.bookshop.pojo;


import cdu.lincnaqiang.bookshop.service.OrderService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="uid")

    private User user;

    private String orderNumber;
    private String address;
    private String recipient;
    private String tel;
    private Date createTime;
    private Date payTime;
    private Date deliverTime;
    private Date dealTime;
    private String status;

    @Transient
    private List<OrderItem> orderItems;
    @Transient
    private float amount;
    @Transient
    private int sum;
    @Transient
    private String statusDesc;

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getStatusDesc(){
        if(null!=statusDesc)
            return statusDesc;
        String desc ="未知";
        switch(status){
            case OrderService.waitPay:
                desc="待支付";
                break;
            case OrderService.waitDelivery:
                desc="待发货";
                break;
            case OrderService.waitConfirm:
                desc="待收货";
                break;
            case OrderService.waitReview:
                desc="待评价";
                break;
            case OrderService.finish:
                desc="已完成";
                break;
            case OrderService.delete:
                desc="已刪除";
                break;
            default:
                desc="未知";
        }
        statusDesc = desc;
        return statusDesc;
    }
}
