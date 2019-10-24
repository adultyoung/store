package by.ffefi.store.service;

import by.ffefi.store.dao.CatDao;
import by.ffefi.store.domain.Cat;
import by.ffefi.store.dto.ItemPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {

    private final CatDao catDao;

    public CatService(CatDao catDao) {
        this.catDao = catDao;
    }

    public List findAll() {
        return catDao.findAll();
    }

    public ItemPageDto findForUser(Pageable pageable) {

        Page<Cat> page = catDao.findAll(pageable);

        return new ItemPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public ItemPageDto findByType(Pageable pageable, String s) {
        Page<Cat> page = catDao.findByType(pageable, s);

        return new ItemPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public ItemPageDto findByTypeAndCompany(Pageable pageable, String s, String s1) {

        Page<Cat> page = catDao.findByTypeAndCompany(pageable, s, s1);

        return new ItemPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );

    }
}
