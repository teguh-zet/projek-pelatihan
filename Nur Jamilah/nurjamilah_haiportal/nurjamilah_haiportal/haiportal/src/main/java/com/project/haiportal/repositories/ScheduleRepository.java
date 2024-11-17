package com.project.haiportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.haiportal.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
    Schedule getReferenceById(Integer id);
}
