package by.ffefi.store.controller;

import by.ffefi.store.domain.User;
import by.ffefi.store.dto.ItemPageDto;
import by.ffefi.store.service.DogService;
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

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final DogService dogService;
    private final ObjectWriter itemWriter;


    @Autowired
    public MainController(DogService dogService, ObjectMapper mapper) {
        this.dogService = dogService;
        ObjectMapper objectMapper = mapper
                .setConfig(mapper.getSerializationConfig());
        this.itemWriter = objectMapper
                .writer();
    }

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {

        HashMap<Object, Object> data = new HashMap<>();
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(0, ItemController.ITEMS_PER_PAGE, sort);
        ItemPageDto itemPageDto = dogService.findForUser(pageRequest);
        data.put("currentPage", itemPageDto.getCurrentPage());
        data.put("totalPages", itemPageDto.getTotalPages());
        String items = itemWriter.writeValueAsString(itemPageDto.getItems());
        model.addAttribute("items", items);
        model.addAttribute("frontendData", data);

        return "index";
    }
}
