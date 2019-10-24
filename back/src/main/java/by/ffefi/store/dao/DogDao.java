package by.ffefi.store.dao;

import by.ffefi.store.domain.Dog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogDao extends JpaRepository<Dog, String> {
    Page<Dog> findAll(Pageable pageable);

    List<Dog> findByTitleContainingIgnoreCase(String text);

    Page<Dog> findByType(Pageable pageable, String type);

    Page<Dog> findByTypeAndCompany(Pageable pageable, String type, String company);
}
