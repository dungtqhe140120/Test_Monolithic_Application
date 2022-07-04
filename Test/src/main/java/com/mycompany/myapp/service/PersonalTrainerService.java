package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PersonalTrainerDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.PersonalTrainer}.
 */
public interface PersonalTrainerService {
    /**
     * Save a personalTrainer.
     *
     * @param personalTrainerDTO the entity to save.
     * @return the persisted entity.
     */
    PersonalTrainerDTO save(PersonalTrainerDTO personalTrainerDTO);

    /**
     * Partially updates a personalTrainer.
     *
     * @param personalTrainerDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PersonalTrainerDTO> partialUpdate(PersonalTrainerDTO personalTrainerDTO);

    /**
     * Get all the personalTrainers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PersonalTrainerDTO> findAll(Pageable pageable);

    /**
     * Get the "id" personalTrainer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PersonalTrainerDTO> findOne(Long id);

    /**
     * Delete the "id" personalTrainer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
