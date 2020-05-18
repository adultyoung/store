package by.ffefi.store.controller;

import by.ffefi.store.domain.Item;
import by.ffefi.store.domain.Opder;
import by.ffefi.store.domain.User;
import by.ffefi.store.dto.Address;
import by.ffefi.store.dto.FeedTypeDto;
import by.ffefi.store.dto.ItemPageDto;
import by.ffefi.store.service.ItemService;
import by.ffefi.store.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final ItemService itemService;
    private final OrderService orderService;
    private final ObjectWriter itemWriter;


    @Autowired
    public MainController(ItemService itemService, ObjectMapper mapper, OrderService orderService) {
        this.itemService = itemService;
        this.orderService = orderService;
        ObjectMapper objectMapper = mapper
                .setConfig(mapper.getSerializationConfig());
        this.itemWriter = objectMapper
                .writer();
    }

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {

        List itemsList = itemService.findAll();
        String items = itemWriter.writeValueAsString(itemsList);
        model.addAttribute("items", items);
        model.addAttribute("currentPage", 0);
        model.addAttribute("totalPages", 0);
        HashMap<Object, Object> data = new HashMap<>();
        HashMap<Object, Object> dogFeedTypeList = new HashMap<>();
        dogFeedTypeList.put("pet", "dog");
        data.put("dogFeedTypes", getAllTypesOfItem("dog", dogFeedTypeList));
        HashMap<Object, Object> catFeedTypeList = new HashMap<>();
        catFeedTypeList.put("pet", "cat");
        data.put("catFeedTypes", getAllTypesOfItem("cat", catFeedTypeList));
        data.put("basket", new Opder());
        data.put("currentUnpaidOrder", null);
        if (user != null) {
            data.put("profile", user);
            data.put("orders", orderService.getOpdersForUser(user.getId()));
        } else {
            data.put("profile", null);
            data.put("address", new Address());
        }

            model.addAttribute("frontendData", data);
        return "index";
    }

    private HashMap<Object, Object> getAllTypesOfItem(String pet, HashMap<Object, Object> feedTypeList) {
        List<String> types = itemService.findTypes(pet);
        List<FeedTypeDto> typeMap = new ArrayList<>();
        types.iterator().forEachRemaining(type -> {
            typeMap.add(new FeedTypeDto(type, itemService.findCompanyByTypeAndPet(type, pet)));
        });

        feedTypeList.put("types", typeMap);
        return feedTypeList;
    }
}
