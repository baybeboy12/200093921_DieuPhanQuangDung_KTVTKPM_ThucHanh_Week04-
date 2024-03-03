package org.example.controller;

import org.example.Repository.OrderRepository;
import org.example.models.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/read")
    public List<Orders> getOrders() {
        return orderRepository.findAll();
    }

    @PostMapping("/create")
    public Orders createOrders(@RequestBody Orders orders) {
        return orderRepository.save(orders);
    }

    @PutMapping("/update/{id}")
    public Orders updateOrders(@PathVariable("id") Long orderId, @RequestBody Orders updatedOrder) {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);

            Orders order = optionalOrder.get();
            order.setTenSanPham(updatedOrder.getTenSanPham());
            return orderRepository.save(order);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long orderId) {
        orderRepository.deleteById(orderId);
        return "Deleted Successfully";
    }
}
