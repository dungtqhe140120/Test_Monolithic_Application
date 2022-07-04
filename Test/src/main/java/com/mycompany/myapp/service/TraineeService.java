package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TraineeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Trainee}.
 */
public interface TraineeService {
    /**
     * Save a trainee.
     *
     * @param traineeDTO the entity to save.
     * @return the persisted entity.
     */
    TraineeDTO save(TraineeDTO traineeDTO);

    /**
     * Partially updates a trainee.
     *
     * @param traineeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TraineeDTO> partialUpdate(TraineeDTO traineeDTO);

    /**
     * Get all the trainees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TraineeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" trainee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TraineeDTO> findOne(Long id);

    /**
     * Delete the "id" trainee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
