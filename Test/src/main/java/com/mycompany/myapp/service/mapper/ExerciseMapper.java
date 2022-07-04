package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Exercise;
import com.mycompany.myapp.service.dto.ExerciseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Exercise} and its DTO {@link ExerciseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ExerciseMapper extends EntityMapper<ExerciseDTO, Exercise> {}
