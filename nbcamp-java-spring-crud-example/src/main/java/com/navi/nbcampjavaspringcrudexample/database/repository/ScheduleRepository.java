package com.navi.nbcampjavaspringcrudexample.database.repository;

import com.navi.nbcampjavaspringcrudexample.database.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
