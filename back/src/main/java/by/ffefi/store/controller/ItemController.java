package by.ffefi.store.controller;

import by.ffefi.store.dto.ItemPageDto;
import by.ffefi.store.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    public static final int ITEMS_PER_PAGE = 10;

    private final DogService itemService;

    @Autowired
    public ItemController(DogService itemService) {
        this.itemService = itemService;
    }


    @GetMapping
    public ItemPageDto getListOfItems(@PageableDefault(size = ITEMS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return itemService.findForUser(pageable);
    }
}
