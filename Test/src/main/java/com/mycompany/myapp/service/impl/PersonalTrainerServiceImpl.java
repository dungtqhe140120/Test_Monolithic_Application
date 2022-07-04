package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.PersonalTrainer;
import com.mycompany.myapp.repository.PersonalTrainerRepository;
import com.mycompany.myapp.service.PersonalTrainerService;
import com.mycompany.myapp.service.dto.PersonalTrainerDTO;
import com.mycompany.myapp.service.mapper.PersonalTrainerMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PersonalTrainer}.
 */
@Service
@Transactional
public class PersonalTrainerServiceImpl implements PersonalTrainerService {

    private final Logger log = LoggerFactory.getLogger(PersonalTrainerServiceImpl.class);

    private final PersonalTrainerRepository personalTrainerRepository;

    private final PersonalTrainerMapper personalTrainerMapper;

    public PersonalTrainerServiceImpl(PersonalTrainerRepository personalTrainerRepository, PersonalTrainerMapper personalTrainerMapper) {
        this.personalTrainerRepository = personalTrainerRepository;
        this.personalTrainerMapper = personalTrainerMapper;
    }

    @Override
    public PersonalTrainerDTO save(PersonalTrainerDTO personalTrainerDTO) {
        log.debug("Request to save PersonalTrainer : {}", personalTrainerDTO);
        PersonalTrainer personalTrainer = personalTrainerMapper.toEntity(personalTrainerDTO);
        personalTrainer = personalTrainerRepository.save(personalTrainer);
        return personalTrainerMapper.toDto(personalTrainer);
    }

    @Override
    public Optional<PersonalTrainerDTO> partialUpdate(PersonalTrainerDTO personalTrainerDTO) {
        log.debug("Request to partially update PersonalTrainer : {}", personalTrainerDTO);

        return personalTrainerRepository
            .findById(personalTrainerDTO.getId())
            .map(existingPersonalTrainer -> {
                personalTrainerMapper.partialUpdate(existingPersonalTrainer, personalTrainerDTO);

                return existingPersonalTrainer;
            })
            .map(personalTrainerRepository::save)
            .map(personalTrainerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PersonalTrainerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PersonalTrainers");
        return personalTrainerRepository.findAll(pageable).map(personalTrainerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonalTrainerDTO> findOne(Long id) {
        log.debug("Request to get PersonalTrainer : {}", id);
        return personalTrainerRepository.findById(id).map(personalTrainerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PersonalTrainer : {}", id);
        personalTrainerRepository.deleteById(id);
    }
}
