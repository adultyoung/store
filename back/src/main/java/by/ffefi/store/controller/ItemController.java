package by.ffefi.store.controller;

import by.ffefi.store.domain.Item;
import by.ffefi.store.dto.ItemPageDto;
import by.ffefi.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    public static final int ITEMS_PER_PAGE = 10;

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping
    public ItemPageDto getListOfItems(@PageableDefault(size = ITEMS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return itemService.findForUser(pageable);
    }

    @GetMapping("{id}")
    public Item getItemById(@PathVariable("id") String id) {
        return itemService.findById(id);
    }
}
