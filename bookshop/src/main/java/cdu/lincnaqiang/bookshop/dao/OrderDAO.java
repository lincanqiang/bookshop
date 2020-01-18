package cdu.lincnaqiang.bookshop.dao;

import cdu.lincnaqiang.bookshop.pojo.Order;
import cdu.lincnaqiang.bookshop.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Integer> {
    public List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status);
}
