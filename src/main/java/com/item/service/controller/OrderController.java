package com.item.service.controller;

import com.item.service.dto.OrderDTO;
import com.item.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> orderList = orderService.findAll();
        if (orderList.isEmpty()) {
            log.warn("Order list is empty");
        }
        return ResponseEntity.ok().body(orderList);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.create(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"id\":\"" + createdOrder.getId() + "\"}");
    }

}

