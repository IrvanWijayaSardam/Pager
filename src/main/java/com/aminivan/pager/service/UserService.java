package com.aminivan.pager.service;

import com.aminivan.pager.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAll(Pageable pageable);

    User findById(int id);

    User save(User user);

    User update(User user);

    void delete(int id);

}
