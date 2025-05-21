package com.teamone.tyremonitoring.repository;

import com.teamone.tyremonitoring.model.TyreData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import java.util.List;
//import org.springframework.data.domain.Pageable;


@Repository
public interface TyreDataRepository extends JpaRepository<TyreData, Long> {
	List<TyreData> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Optional: For quick "last N days" queries
    List<TyreData> findByTimestampAfter(LocalDateTime date);
    
    @Query(value = "SELECT * FROM tyre_data ORDER BY timestamp DESC LIMIT 1", nativeQuery = true)
    TyreData findTop1ByOrderByTimestampDesc();
}
