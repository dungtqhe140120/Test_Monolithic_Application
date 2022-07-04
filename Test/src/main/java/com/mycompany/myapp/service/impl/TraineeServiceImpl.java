package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Trainee;
import com.mycompany.myapp.repository.TraineeRepository;
import com.mycompany.myapp.service.TraineeService;
import com.mycompany.myapp.service.dto.TraineeDTO;
import com.mycompany.myapp.service.mapper.TraineeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Trainee}.
 */
@Service
@Transactional
public class TraineeServiceImpl implements TraineeService {

    private final Logger log = LoggerFactory.getLogger(TraineeServiceImpl.class);

    private final TraineeRepository traineeRepository;

    private final TraineeMapper traineeMapper;

    public TraineeServiceImpl(TraineeRepository traineeRepository, TraineeMapper traineeMapper) {
        this.traineeRepository = traineeRepository;
        this.traineeMapper = traineeMapper;
    }

    @Override
    public TraineeDTO save(TraineeDTO traineeDTO) {
        log.debug("Request to save Trainee : {}", traineeDTO);
        Trainee trainee = traineeMapper.toEntity(traineeDTO);
        trainee = traineeRepository.save(trainee);
        return traineeMapper.toDto(trainee);
    }

    @Override
    public Optional<TraineeDTO> partialUpdate(TraineeDTO traineeDTO) {
        log.debug("Request to partially update Trainee : {}", traineeDTO);

        return traineeRepository
            .findById(traineeDTO.getId())
            .map(existingTrainee -> {
                traineeMapper.partialUpdate(existingTrainee, traineeDTO);

                return existingTrainee;
            })
            .map(traineeRepository::save)
            .map(traineeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TraineeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Trainees");
        return traineeRepository.findAll(pageable).map(traineeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TraineeDTO> findOne(Long id) {
        log.debug("Request to get Trainee : {}", id);
        return traineeRepository.findById(id).map(traineeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Trainee : {}", id);
        traineeRepository.deleteById(id);
    }
}
