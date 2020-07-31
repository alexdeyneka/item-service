package com.item.service.service;

import com.item.service.dto.ItemDTO;
import com.item.service.dto.ItemMapper;
import com.item.service.entity.Item;
import com.item.service.repository.ItemRepo;
import com.item.service.utils.TestDataGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class ItemServiceTest {

    @Mock
    private ItemRepo itemRepo;

    @InjectMocks
    private ItemService itemService;

    private final List<Item> itemList = new TestDataGenerator().generateItemList();

    @Test
    void findAll() {
        when(itemRepo.findAll()).thenReturn(itemList);
        List<ItemDTO> testList = itemService.findAll();
        assertEquals(testList.size(), itemList.size());
        verify(itemRepo, times(1)).findAll();
        verify(itemRepo, only()).findAll();
    }

    @Test
    void findByPrice() {
        when(itemRepo.findAll()).thenReturn(itemList);
        Optional<ItemDTO> optionalItem = itemService.findByPrice(itemList.get(0).getName(), itemList.get(0).getQuantity());
        assertEquals(itemList.get(0), ItemMapper.INSTANCE.itemDTOToItem(optionalItem.get()));
        verify(itemRepo, times(1)).findAll();
        verify(itemRepo, only()).findAll();
    }
}