package by.ffefi.store.controller;

import by.ffefi.store.dto.ItemPageDto;
import by.ffefi.store.service.UtilsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.ffefi.store.controller.ItemController.ITEMS_PER_PAGE;

@RestController
@RequestMapping("/search?={text}")
public class SearchController {

    private final UtilsService utilsService;

    public SearchController(UtilsService utilsService) {
        this.utilsService = utilsService;
    }

    @GetMapping()
    public ItemPageDto searchItem(@PageableDefault(size = ITEMS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable, @PathVariable("text") String text) {
        return utilsService.search(text, pageable);
    }
}
