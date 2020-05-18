package by.ffefi.store.service;

import by.ffefi.store.dao.OrderDao;
import by.ffefi.store.domain.Opder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Opder> findAll() {
        return orderDao.findAll();
    }

    public Opder findById(String id) {
        Optional<Opder> order = orderDao.findById(id);

        return order.orElse(null);
    }

    public Set<Opder> getOpdersForUser(Long id) {
        return orderDao.findByUserId(id);
    }
}
