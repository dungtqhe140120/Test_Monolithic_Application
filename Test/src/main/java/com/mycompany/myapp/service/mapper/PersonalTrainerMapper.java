package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.PersonalTrainer;
import com.mycompany.myapp.service.dto.PersonalTrainerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PersonalTrainer} and its DTO {@link PersonalTrainerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonalTrainerMapper extends EntityMapper<PersonalTrainerDTO, PersonalTrainer> {}
