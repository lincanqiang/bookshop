package cdu.lincnaqiang.bookshop.dao;

import cdu.lincnaqiang.bookshop.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User getByName(String name);
    User getByNameAndPassword(String name, String password);
}
