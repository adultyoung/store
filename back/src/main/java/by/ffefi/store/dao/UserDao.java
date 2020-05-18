package by.ffefi.store.dao;

import by.ffefi.store.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {


    User findByTelephone(String username);

    User findByEmail(String username);
}
