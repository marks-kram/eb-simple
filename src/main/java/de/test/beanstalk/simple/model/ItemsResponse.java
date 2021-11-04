package de.test.beanstalk.simple.model;

import de.test.beanstalk.simple.entity.Item;

import java.util.List;

public class ItemsResponse {

    private String lastAccessed;
    private List<Item> items;

    public ItemsResponse(String lastAccessed, List<Item> items) {
        this.lastAccessed = lastAccessed;
        this.items = items;
    }

    public String getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(String lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
