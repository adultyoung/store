package by.ffefi.store.dao;

import by.ffefi.store.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemDao extends JpaRepository<Item, String> {
    Page<Item> findAll(Pageable pageable);

    List<Item> findByTitleContainingIgnoreCase(String text);

    Page<Item> findByType(Pageable pageable, String type);

    Page<Item> findByTypeAndCompany(Pageable pageable, String type, String company);

    @Query(value = "SELECT DISTINCT type FROM item where pet=:pet", nativeQuery = true)
    List<String> findTypes(@Param("pet")String pet);

    @Query(value = "SELECT DISTINCT company FROM item WHERE type=:type", nativeQuery = true)
    List<String> findCompaniesByType(@Param("type")String type);

    Page<Item> findByPet(Pageable pageable, String pet);

    Page<Item> findByPetAndTypeAndCompany(Pageable pageable, String pet, String type, String company);

    Page<Item> findByPetAndType(Pageable pageable, String pet, String type);

    @Query(value = "SELECT DISTINCT company FROM item WHERE type=:type and pet=:pet", nativeQuery = true)
    List<String> findCompaniesByTypeAndPet(@Param("type")String type, @Param("pet")String pet);
}
