package by.ffefi.store.dao;

import by.ffefi.store.domain.Cat;
import by.ffefi.store.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatDao extends JpaRepository<Cat, String> {
    Page<Cat> findAll(Pageable pageable);

    List<Item> findByTitleContainingIgnoreCase(String text);

    Page<Cat> findByTypeAndCompany(Pageable pageable, String type, String company);

    Page<Cat> findByType(Pageable pageable, String type);
}
