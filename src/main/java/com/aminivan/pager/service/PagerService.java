package com.aminivan.pager.service;

import com.aminivan.pager.models.Pager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagerService {

    Page<Pager> findAll(Pageable pageable);

    Pager findById(int id);

    Pager save(Pager pager);

    Pager update(Pager updatedPager);

    void delete(int id);
}
