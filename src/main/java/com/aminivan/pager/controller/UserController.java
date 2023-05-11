package com.aminivan.pager.controller;

import com.aminivan.pager.auth.AuthKey;
import com.aminivan.pager.models.Pager;
import com.aminivan.pager.models.User;
import com.aminivan.pager.service.UserService;
import com.aminivan.pager.utils.ResponseHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    private static final String SUCCESS_RETRIEVE_MSG = "Successfully retrieved data!";
    private static final String SUCCESS_EDIT_MSG = "Successfully edit data!";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<User> userList;
        Pageable pageable = PageRequest.of(page,size);
        userList = userService.findAll(pageable);

        return ResponseHandler.generatePagingResponse(SUCCESS_RETRIEVE_MSG, HttpStatus.OK,userList);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id){
        var user = userService.findById(id);
        return ResponseHandler.generateResponse(SUCCESS_RETRIEVE_MSG, HttpStatus.OK,user);
    }

    @PostMapping("/user")
    public ResponseEntity<Object> save(@RequestBody User user){
        userService.save(user);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK,user);
    }

    @PutMapping("/user")
    public ResponseEntity<Object> update(@RequestBody User user) {
        userService.update(user);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK, user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id ) {
        userService.delete(id);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK, id);
    }

}
