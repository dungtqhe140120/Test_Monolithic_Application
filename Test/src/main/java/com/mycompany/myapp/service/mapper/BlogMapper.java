package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Blog;
import com.mycompany.myapp.service.dto.BlogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Blog} and its DTO {@link BlogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BlogMapper extends EntityMapper<BlogDTO, Blog> {}
