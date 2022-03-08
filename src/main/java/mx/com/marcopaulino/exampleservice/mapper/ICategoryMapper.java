package mx.com.marcopaulino.exampleservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.com.marcopaulino.exampleservice.dto.CategoryDto;
import mx.com.marcopaulino.exampleservice.entity.Category;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CategoryDto entityToDto(Category entity);

    List<CategoryDto> entitiesToDtos(List<Category> entities);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Category dtoToEntity(CategoryDto dto);

}
