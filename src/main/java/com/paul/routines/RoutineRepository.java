package com.paul.routines;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findByClientId(Long clientId);
}
