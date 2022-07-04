package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Trainee;
import com.mycompany.myapp.service.dto.TraineeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Trainee} and its DTO {@link TraineeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TraineeMapper extends EntityMapper<TraineeDTO, Trainee> {}
