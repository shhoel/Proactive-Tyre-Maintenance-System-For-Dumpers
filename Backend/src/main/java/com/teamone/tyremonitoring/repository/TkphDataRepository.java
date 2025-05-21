package com.teamone.tyremonitoring.repository;

import com.teamone.tyremonitoring.model.TkphData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TkphDataRepository extends JpaRepository<TkphData, Long> {
    List<TkphData> findAllByOrderByTimestampDesc();

}
