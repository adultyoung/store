package by.ffefi.store.controller;

import by.ffefi.store.domain.Opder;
import by.ffefi.store.domain.User;
import by.ffefi.store.service.OrderService;
import by.ffefi.store.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ProfileController {

    private final UserService userService;
    private final OrderService orderService;

    public ProfileController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

//    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
//    private User getProfile(@PathVariable("id") String id, @AuthenticationPrincipal User user) {
//        if (userService.loadUserById(id).equals(user)) {
//            return user;
//        }
//        return null;
//    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
    private String updateProfile(@RequestBody User user, @PathVariable("id") User userFromDb) {
        userService.updateUser(user, userFromDb);
        return "Успешно обновлено";
    }

    @RequestMapping(value = "/opders", method = RequestMethod.GET)
    private Set<Opder> getAllOrders(@AuthenticationPrincipal User user) {
        if (user != null) {
        return orderService.getOpdersForUser(user.getId());
        }
        return null;
    }

    @RequestMapping(value = "/opders/{id}", method = RequestMethod.GET)
    private Opder getOrder(@PathVariable("id") String id, @AuthenticationPrincipal User user) {
        Opder opderFromDb = orderService.findById(id);
        if (!user.getTelephone().equals(opderFromDb.getUserId())) {
            return null;
        }
        return opderFromDb;
    }
}
