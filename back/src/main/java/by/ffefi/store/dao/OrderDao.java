package by.ffefi.store.dao;

import by.ffefi.store.domain.Opder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderDao extends JpaRepository<Opder, String> {
    Set<Opder> findByUserId(Long userId);
}
