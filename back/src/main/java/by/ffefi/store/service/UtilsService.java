package by.ffefi.store.service;

import by.ffefi.store.dao.ItemDao;
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

    private final ItemDao itemDao;

    public UtilsService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public ItemPageDto search(String text, Pageable pageable) {
        List<Item> search = new ArrayList<>();
        search.addAll(itemDao.findByTitleContainingIgnoreCase(text));
        Page<Item> searchPage = new PageImpl<>(search, pageable, search.size());

        return new ItemPageDto(
                searchPage.getContent(),
                pageable.getPageNumber(),
                searchPage.getTotalPages()
        );
    }
}
