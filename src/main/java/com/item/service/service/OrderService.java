package com.item.service.service;

import com.item.service.dto.OrderDTO;
import com.item.service.dto.OrderMapper;
import com.item.service.entity.Order;
import com.item.service.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepo orderRepo;

    public List<OrderDTO> findAll() {
        List<Order> entityList = orderRepo.findAll();
        List<OrderDTO> dtoList = new ArrayList<>();
        entityList.forEach(element -> dtoList.add(OrderMapper.INSTANCE.orderToOrderDTO(element)));
        return dtoList;
    }

    public OrderDTO create(OrderDTO orderDTO) {
        Order savedOrder = orderRepo.save(OrderMapper.INSTANCE.orderDTOToOrder(orderDTO));
        return OrderMapper.INSTANCE.orderToOrderDTO(savedOrder);
    }

}
