package by.ffefi.store.service;

import by.ffefi.store.dao.CatDao;
import by.ffefi.store.dao.DogDao;
import by.ffefi.store.domain.Item;
import by.ffefi.store.dto.ItemPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilsService {

    private final CatDao catDao;
    private final DogDao dogDao;

    public UtilsService(CatDao catDao, DogDao dogDao) {
        this.catDao = catDao;
        this.dogDao = dogDao;
    }

    public ItemPageDto search(String text, Pageable pageable) {
        List<Item> search = new ArrayList<>();
        search.addAll(catDao.findByTitleContainingIgnoreCase(text));
        search.addAll(dogDao.findByTitleContainingIgnoreCase(text));
        Page<Item> searchPage = new PageImpl<>(search, pageable, search.size());

        return new ItemPageDto(
                searchPage.getContent(),
                pageable.getPageNumber(),
                searchPage.getTotalPages()
        );
    }
}
