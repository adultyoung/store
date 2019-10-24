package by.ffefi.store.controller;

import by.ffefi.store.dto.ItemPageDto;
import by.ffefi.store.service.CatService;
import by.ffefi.store.service.DogService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetsController {

    private final CatService catService;
    private final DogService dogService;

    private static final int ITEMS_PER_PAGE = 10;

    public PetsController(CatService catService, DogService dogService) {
        this.catService = catService;
        this.dogService = dogService;
    }

    @RequestMapping(value = "/cat?={type}", method = RequestMethod.GET)
    public ItemPageDto getListOfItems(@PageableDefault(size = ITEMS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable("type") String text) {
        if (!text.isEmpty()) {
            String[] typeAndCompany = text.split("\\?=");
            switch (typeAndCompany.length) {
                case 1:
                    return catService.findByType(pageable, typeAndCompany[0]);
                case 2:
                    return catService.findByTypeAndCompany(pageable, typeAndCompany[0], typeAndCompany[1]);
                default:
                    return null;
            }
        }
        return null;
    }

    @RequestMapping(value = "/cat", method = RequestMethod.GET)
    public ItemPageDto getAllForCats(@PageableDefault(size = ITEMS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return catService.findForUser(pageable);
    }

    @RequestMapping(value = "/dog", method = RequestMethod.GET)
    public ItemPageDto getAllForDogs(@PageableDefault(size = ITEMS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return dogService.findForUser(pageable);
    }

    @RequestMapping(value = "/dog?={type}", method = RequestMethod.GET)
    public ItemPageDto getListOfItem(@PageableDefault(size = ITEMS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable("type") String text) {
        if (!text.isEmpty()) {
            String[] typeAndCompany = text.split("\\?=");
            switch (typeAndCompany.length) {
                case 1:
                    return dogService.findByType(pageable, typeAndCompany[0]);
                case 2:
                    return dogService.findByTypeAndCompany(pageable, typeAndCompany[0], typeAndCompany[1]);
                default:
                    return null;
            }
        }
        return null;
    }

}
