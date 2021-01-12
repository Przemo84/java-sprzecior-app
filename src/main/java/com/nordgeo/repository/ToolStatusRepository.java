package com.nordgeo.repository;

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

//    @Query(value = "select ts, avg(ts.rating) as averageOfRating FROM tool_status ts WHERE ts.tool_id = :toolId order by ts.create_date desc", nativeQuery = true)
//    Page<ToolStatus> findAllToolStatuses(Pageable pageable, @Param("toolId") Integer toolId);


    @Query(value = "select avg(ts.rating) FROM ToolStatus ts WHERE ts.tool.id = :toolId")
    Double findAverageOfRatings(@Param("toolId") Integer toolId);
}
