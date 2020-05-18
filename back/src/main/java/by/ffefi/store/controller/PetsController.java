//package by.ffefi.store.controller;
//
//import by.ffefi.store.dto.ItemPageDto;
//import by.ffefi.store.service.ItemService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/pets")
//public class PetsController {
//
//    private final ItemService itemService;
//    private final ObjectWriter itemWriter;
//
//
//    private static final int ITEMS_PER_PAGE = 10;
//
//    public PetsController(ItemService itemService, ObjectMapper mapper) {
//        this.itemService = itemService;
//        ObjectMapper objectMapper = mapper
//                .setConfig(mapper.getSerializationConfig());
//        this.itemWriter = objectMapper
//                .writer();
//    }
//
//   @GetMapping("{pet}")
//    public ItemPageDto getListOfItemByPet
//            (
//                    @PageableDefault(size = ITEMS_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
//                    @PathVariable("pet") String pet
//            ) {
//        String[] split = pet.split("-");
//        if (split.length == 3) {
//            return itemService.findByPetAndTypeAndCompany(pageable, split[0], split[1], split[2]);
//        } else if (split.length == 2) {
//            return itemService.findByPetAndType(pageable, split[0], split[1]);
//        } else {
//            return itemService.findByPet(pageable, split[0]);
//        }
//    }
//}
