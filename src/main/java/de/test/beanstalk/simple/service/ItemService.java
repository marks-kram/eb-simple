package de.test.beanstalk.simple.service;

import de.test.beanstalk.simple.entity.Item;
import de.test.beanstalk.simple.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(String name, String description){
        Item item = new Item(name, description);
        return itemRepository.save(item);
    }

    public List<Item> getAll(){
        List<Item> list = new ArrayList<>();
        Iterable<Item> items = itemRepository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Item> getItem(String uuid){
        return itemRepository.findByUuid(uuid);
    }

    public Optional<Item> modifyItem(String uuid, String name, String description){
        Optional<Item> itemOptional = itemRepository.findByUuid(uuid);
        if(itemOptional.isEmpty()){
            return Optional.empty();
        }
        Item item = itemOptional.get();
        item.setName(name);
        item.setDescription(description);
        return Optional.of(itemRepository.save(item));
    }

    public boolean removeItem(String uuid){
        boolean exists = itemRepository.existsByUuid(uuid);
        itemRepository.deleteByUuid(uuid);
        return exists;
    }
}
