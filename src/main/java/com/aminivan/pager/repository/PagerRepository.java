package com.aminivan.pager.repository;

import com.aminivan.pager.models.Pager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagerRepository extends JpaRepository<Pager,Integer> {
}
