package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Profile;
import com.mycompany.myapp.service.dto.ProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Profile} and its DTO {@link ProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfileMapper extends EntityMapper<ProfileDTO, Profile> {}
