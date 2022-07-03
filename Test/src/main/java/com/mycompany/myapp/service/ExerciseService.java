package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Exercise;
import com.mycompany.myapp.repository.ExerciseRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Exercise}.
 */
@Service
@Transactional
public class ExerciseService {

    private final Logger log = LoggerFactory.getLogger(ExerciseService.class);

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    /**
     * Save a exercise.
     *
     * @param exercise the entity to save.
     * @return the persisted entity.
     */
    public Exercise save(Exercise exercise) {
        log.debug("Request to save Exercise : {}", exercise);
        return exerciseRepository.save(exercise);
    }

    /**
     * Partially update a exercise.
     *
     * @param exercise the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Exercise> partialUpdate(Exercise exercise) {
        log.debug("Request to partially update Exercise : {}", exercise);

        return exerciseRepository
            .findById(exercise.getId())
            .map(existingExercise -> {
                if (exercise.getShort_description() != null) {
                    existingExercise.setShort_description(exercise.getShort_description());
                }
                if (exercise.getFull_description() != null) {
                    existingExercise.setFull_description(exercise.getFull_description());
                }
                if (exercise.getCreate_by() != null) {
                    existingExercise.setCreate_by(exercise.getCreate_by());
                }
                if (exercise.getData_url() != null) {
                    existingExercise.setData_url(exercise.getData_url());
                }

                return existingExercise;
            })
            .map(exerciseRepository::save);
    }

    /**
     * Get all the exercises.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Exercise> findAll(Pageable pageable) {
        log.debug("Request to get all Exercises");
        return exerciseRepository.findAll(pageable);
    }

    /**
     * Get one exercise by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Exercise> findOne(Long id) {
        log.debug("Request to get Exercise : {}", id);
        return exerciseRepository.findById(id);
    }

    /**
     * Delete the exercise by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Exercise : {}", id);
        exerciseRepository.deleteById(id);
    }
}
