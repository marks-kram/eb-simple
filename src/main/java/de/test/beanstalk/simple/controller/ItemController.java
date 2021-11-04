package de.test.beanstalk.simple.controller;

import de.test.beanstalk.simple.entity.Item;
import de.test.beanstalk.simple.model.ItemRequest;
import de.test.beanstalk.simple.model.ItemsResponse;
import de.test.beanstalk.simple.model.RemovedResponse;
import de.test.beanstalk.simple.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    private final ItemService itemService;
    private static final String COOKIE_NAME = "last_access";

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Item> addItem(@RequestBody ItemRequest itemRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.addItem(itemRequest.getName(), itemRequest.getDescription()));
    }

    @GetMapping(value = "/get/{uuid}")
    public ResponseEntity<Item> getItem(@PathVariable String uuid){
        Optional<Item> itemOptional = itemService.getItem(uuid);
        if(itemOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(itemOptional.get());
    }

    @GetMapping(value = "/list")
    public ResponseEntity<ItemsResponse> getAll(HttpServletRequest request, HttpServletResponse response){
        String lastAccessed = handleCookie(request, response);
        return ResponseEntity.status(HttpStatus.OK).body(new ItemsResponse(lastAccessed, itemService.getAll()));
    }

    @PutMapping(value = "/modify/{uuid}")
    public ResponseEntity<Item> modifyItem(@RequestBody ItemRequest itemRequest, @PathVariable String uuid){
        Optional<Item> itemOptional = itemService.modifyItem(uuid, itemRequest.getName(), itemRequest.getDescription());
        if(itemOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(itemOptional.get());
    }

    @DeleteMapping(value = "/remove/{uuid}")
    public ResponseEntity<RemovedResponse> modifyItem(@PathVariable String uuid){
        boolean removed = itemService.removeItem(uuid);
        if(!removed){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new RemovedResponse(uuid));
    }

    private String handleCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = WebUtils.getCookie(request, COOKIE_NAME);
        String now = (System.currentTimeMillis()/1000)+"";
        setCookie(response, now);
        return (cookie == null || cookie.getValue() == null) ? "-1" : cookie.getValue();
    }

    private void setCookie(HttpServletResponse response, String now){
        Cookie cookie = new Cookie(COOKIE_NAME, now);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

}
