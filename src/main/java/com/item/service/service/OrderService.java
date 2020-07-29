package com.item.service.service;

import com.item.service.dto.OrderDTO;
import com.item.service.dto.OrderMapper;
import com.item.service.entity.Order;
import com.item.service.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepo orderRepo;

    public List<OrderDTO> findAll() {
        List<Order> list = orderRepo.findAll();
        List<OrderDTO> result = new ArrayList<>();
        list.forEach(n -> result.add(OrderMapper.INSTANCE.productToProductDTO(n)));
        return result;
    }

    public Optional<Order> findById(Integer id) {

        return orderRepo.findById(id);
    }

    public Order create(Order order) {

        return orderRepo.save(order);
    }

    public void delete(LocalDate localDate) {

    }
}
