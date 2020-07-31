package com.item.service.service;

import com.item.service.dto.OrderDTO;
import com.item.service.dto.OrderMapper;
import com.item.service.entity.Order;
import com.item.service.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepo orderRepo;
    // the storage, where each order lives for 10 minutes
    private final Map<LocalDate, Order> storage = new PassiveExpiringMap<>(10, TimeUnit.MINUTES);

    // gets full orders history from the db
    public List<OrderDTO> findAll() {
        List<Order> entityList = orderRepo.findAll();
        List<OrderDTO> dtoList = new ArrayList<>();
        entityList.forEach(element -> dtoList.add(OrderMapper.INSTANCE.orderToOrderDTO(element)));
        return dtoList;
    }

    // creates new order, saves it to the db, and puts it into the storage
    public OrderDTO create(OrderDTO orderDTO) {
        Order savedOrder = orderRepo.save(OrderMapper.INSTANCE.orderDTOToOrder(orderDTO));
        storage.put(LocalDate.now(), savedOrder);
        return OrderMapper.INSTANCE.orderToOrderDTO(savedOrder);
    }

    // displays active orders in the storage
    public List<OrderDTO> displayActiveOrders() {
        List<OrderDTO> activeOrdersList = new ArrayList<>();
        storage.forEach((key,value) -> activeOrdersList.add(OrderMapper.INSTANCE.orderToOrderDTO(value)));
        return activeOrdersList;
    }

}
