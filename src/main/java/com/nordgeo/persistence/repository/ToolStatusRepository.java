package com.nordgeo.persistence.repository;

import com.nordgeo.entity.ToolStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolStatusRepository extends PagingAndSortingRepository<ToolStatus,Integer> {

    @Query(value = "select ts FROM ToolStatus ts WHERE ts.tool.id = :toolId order by ts.createDate desc")
    Page<ToolStatus> findAllToolStatuses(Pageable pageable, @Param("toolId") Integer toolId);
}
