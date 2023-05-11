package com.aminivan.pager.controller;

import com.aminivan.pager.auth.AuthKey;
import com.aminivan.pager.models.Pager;
import com.aminivan.pager.service.PagerService;
import com.aminivan.pager.utils.ResponseHandler;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PagerController {
    private final PagerService pagerService;

    private static final String SUCCESS_RETRIEVE_MSG = "Successfully retrieved data!";
    private static final String SUCCESS_EDIT_MSG = "Successfully edit data!";

    private static final String UNAUTHORIZED_MSG = "Unauthorized !";


    @Autowired
    public PagerController(PagerService pagerService) {
        this.pagerService = pagerService;
    }

    @GetMapping("/pager")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(name = "Request-Id", required = false) String requestId
    ) {
//        if(requestId == null){
//            return ResponseHandler.generateResponse(UNAUTHORIZED_MSG, HttpStatus.OK,"Please check your request");
//        }
//
//        if(!requestId.equals(AuthKey.getKey())){
//            return ResponseHandler.generateResponse(UNAUTHORIZED_MSG, HttpStatus.OK,"Invalid Credentials");
//        }

        Page<Pager> pagerList;
        Pageable pageable = PageRequest.of(page,size);
        pagerList = pagerService.findAll(pageable);

        return ResponseHandler.generatePagingResponse(SUCCESS_RETRIEVE_MSG, HttpStatus.OK,pagerList);
    }


    @GetMapping("/pager/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id){
        var pager = pagerService.findById(id);
        return ResponseHandler.generateResponse(SUCCESS_RETRIEVE_MSG, HttpStatus.OK,pager);
    }

    @PostMapping("/pager")
    public ResponseEntity<Object> save(@RequestBody Pager pager){
        //ngapa salah dah ?
        pagerService.save(pager);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK,pager);
    }

    @PutMapping("/pager")
    public ResponseEntity<Object> update(@RequestBody Pager pager) {
        pagerService.update(pager);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK, pager);
    }

    @DeleteMapping("/pager/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id ) {
        pagerService.delete(id);
        return ResponseHandler.generateResponse(SUCCESS_EDIT_MSG, HttpStatus.OK, id);
    }

}
