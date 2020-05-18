package by.ffefi.store.controller;

import by.ffefi.store.dao.OrderDao;
import by.ffefi.store.domain.Opder;
import by.ffefi.store.domain.User;
import by.ffefi.store.dto.OrderStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderDao orderDao;

    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @PostMapping
    public Opder saveOrder(@RequestBody Opder opder, @AuthenticationPrincipal() User user) {
        if (user != null) {
            opder.setUserId(user.getId());
        }
        opder.setCreatedAt(LocalDateTime.now());
        opder.setStatus(OrderStatus.NOTPAYED);
        opder.setModifiedAt(LocalDateTime.now());
        orderDao.save(opder);
        return opder;
    }
}
