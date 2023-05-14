package com.aminivan.pager.service;

import com.aminivan.pager.models.Pager;
import com.aminivan.pager.repository.PagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PagerServiceImpl implements PagerService{

    private final PagerRepository pagerRepository;
    @Autowired
    public PagerServiceImpl(PagerRepository pagerRepository) {this.pagerRepository = pagerRepository;}

    @Override
    public Page<Pager> findAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("xstatus").descending());
        return pagerRepository.findAll(pageable);
    }


    @Override
    public Pager findById(int id) {
        var pager = pagerRepository.findById(id);
        if(pager.isEmpty()) throw new RuntimeException("Data Pager Id : " + id + "Is Not Exist.");
        return pager.get();
    }

    @Override
    public Pager save(Pager pager) {
        if(pager.getPagername() == null || pager.getSsid() == null
        || pager.getSsidpass() == null || pager.getXstatus() == null)
            throw new RuntimeException("Pager Data Not Valid");

        return pagerRepository.save(pager);
    }

    @Override
    public Pager update(Pager updatedPager) {
        var result = pagerRepository.findById(updatedPager.getPagerid());

        if(result.isEmpty()) throw new RuntimeException("Pager Data Not Found");
        var pager = result.get();
        pager.setPagername(updatedPager.getPagername());
        pager.setSsid(updatedPager.getSsid());
        pager.setSsidpass(updatedPager.getSsidpass());
        pager.setXstatus(updatedPager.getXstatus());

        return pagerRepository.save(pager);

    }

    @Override
    public void delete(int id) {
        var result = pagerRepository.findById(id);

        if(result.isEmpty()) throw new RuntimeException("No Data Found");

        pagerRepository.delete(result.get());
    }


}
