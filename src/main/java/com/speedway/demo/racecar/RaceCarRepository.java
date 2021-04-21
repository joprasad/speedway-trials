package com.speedway.demo.racecar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceCarRepository extends JpaRepository<RaceCarEntity, Long> {
}
