package com.navi.nbcampjpaspringtestexample.database.repository;

import com.navi.nbcampjpaspringtestexample.database.entity.Store;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT s FROM Store s WHERE s.openAt <= :currentTime AND s.closeAt > :currentTime")
    List<Store> findStoresOpenAt(@Param("currentTime") LocalTime currentTime);

}
