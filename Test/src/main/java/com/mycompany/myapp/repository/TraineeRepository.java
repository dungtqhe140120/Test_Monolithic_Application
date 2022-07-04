package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Trainee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Trainee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {}
