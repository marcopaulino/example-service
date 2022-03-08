package mx.com.marcopaulino.exampleservice.service;

import java.util.List;
import java.util.Optional;

import mx.com.marcopaulino.exampleservice.dto.CategoryDto;

public interface ICategoryService {

    List<CategoryDto> getCategories();

    Optional<CategoryDto> findCategory(Long id);

    CategoryDto saveCategory(CategoryDto dto);

    Optional<CategoryDto> updateCategory(Long id, CategoryDto dto);

    boolean deleteCategory(Long id);

}
