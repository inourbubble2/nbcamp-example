package com.navi.nbcampjavaspringcrudexample.api.controller;

import com.navi.nbcampjavaspringcrudexample.api.model.ScheduleCreateRequest;
import com.navi.nbcampjavaspringcrudexample.api.model.ScheduleUpdateRequest;
import com.navi.nbcampjavaspringcrudexample.database.entity.Schedule;
import com.navi.nbcampjavaspringcrudexample.database.repository.ScheduleRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;

    @PostMapping("/schedules")
    public Schedule createSchedule(@RequestBody ScheduleCreateRequest request) {
        Schedule schedule = new Schedule(
            request.getTitle(),
            request.getContents(),
            request.getMemberId(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        return scheduleRepository.save(schedule);
    }

    @PostMapping("/schedules/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("스케줄이 없습니다!!"));

        schedule.update(request.getTitle(), request.getContents(), LocalDateTime.now());

        return scheduleRepository.save(schedule);
    }

    @GetMapping("/schedules")
    public List<Schedule> findAllSchedule() {
        return scheduleRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    @DeleteMapping("/schedules/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("스케줄이 없습니다!!!!"));

        scheduleRepository.delete(schedule);
    }
}
