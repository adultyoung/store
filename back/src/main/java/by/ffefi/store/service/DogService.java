package by.ffefi.store.service;

import by.ffefi.store.dao.DogDao;
import by.ffefi.store.domain.Dog;
import by.ffefi.store.dto.ItemPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {

    private final DogDao dogDao;

    public DogService(DogDao dogDao) {
        this.dogDao = dogDao;
    }

    public List findAll() {
        return dogDao.findAll();
    }

    public ItemPageDto findForUser(Pageable pageable) {

        Page<Dog> page = dogDao.findAll(pageable);

        return new ItemPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public ItemPageDto findByType(Pageable pageable, String s) {

        Page<Dog> page = dogDao.findByType(pageable, s);

        return new ItemPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public ItemPageDto findByTypeAndCompany(Pageable pageable, String s, String s1) {
        Page<Dog> page = dogDao.findByTypeAndCompany(pageable, s, s1);

        return new ItemPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );

    }
}
