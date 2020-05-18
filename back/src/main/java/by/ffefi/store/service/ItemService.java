package by.ffefi.store.service;

import by.ffefi.store.dao.ItemDao;
import by.ffefi.store.domain.Item;
import by.ffefi.store.dto.ItemPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemDao itemDao;

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public List findAll() {
        return itemDao.findAll();
    }

    public List<String> findTypes(String pet) {
        return itemDao.findTypes(pet);
    }

    public List<String> findCompanyByTypeAndPet(String type, String pet) {
        return itemDao.findCompaniesByTypeAndPet(type, pet);
    }

    public ItemPageDto findForUser(Pageable pageable) {

        Page<Item> page = itemDao.findAll(pageable);

        return new ItemPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public ItemPageDto findByPet(Pageable pageable, String pet) {
        Page<Item> page = itemDao.findByPet(pageable, pet);

        return new ItemPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public ItemPageDto findByPetAndTypeAndCompany(Pageable pageable, String pet, String type, String company) {
        Page<Item> page = itemDao.findByPetAndTypeAndCompany(pageable, pet, type, company);

        return new ItemPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );

    }

    public ItemPageDto findByPetAndType(Pageable pageable, String pet, String type) {
        Page<Item> page = itemDao.findByPetAndType(pageable, pet, type);

        return new ItemPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public Item findById(String id) {
        Optional<Item> item = itemDao.findById(id);
        return item.isPresent() ? item.get() : null;
    }
}
