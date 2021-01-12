package com.nordgeo.repository;

import com.nordgeo.entity.ActionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ActionHistoryRepository extends PagingAndSortingRepository<ActionHistory, Integer> {

    @Query(value = "SELECT a FROM ActionHistory a" )
    Page<ActionHistory> findAll(Pageable pageable);

    Iterable<ActionHistory> findAllByOrderByIdDesc();

    @Query(value = "select ah from ActionHistory ah where ah.createDate between :startDate and :endDate")
    Page<ActionHistory> findAllBetweenDates(Pageable pageable, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "select ah from ActionHistory ah where ah.createDate between :startDate and :endDate order by ah.id desc ")
    Iterable<ActionHistory> findAllBetweenDatesForExport(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
