package mx.com.marcopaulino.exampleservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mx.com.marcopaulino.exampleservice.dto.CategoryDto;
import mx.com.marcopaulino.exampleservice.entity.Category;
import mx.com.marcopaulino.exampleservice.mapper.ICategoryMapper;
import mx.com.marcopaulino.exampleservice.repository.ICategoryRepository;
import mx.com.marcopaulino.exampleservice.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;

    public CategoryServiceImpl(ICategoryRepository categoryRepository, ICategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getCategories() {
        return categoryMapper.entitiesToDtos(categoryRepository.findByDeletedAtIsNull());
    }

    @Override
    public Optional<CategoryDto> findCategory(Long id) {
        Optional<Category> optionalEntity = categoryRepository.findByIdAndDeletedAtIsNull(id);
        return optionalEntity.map(categoryMapper::entityToDto);
    }

    @Override
    public CategoryDto saveCategory(CategoryDto dto) {
        Category entity = categoryMapper.dtoToEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        return categoryMapper.entityToDto(categoryRepository.save(entity));
    }

    @Override
    public Optional<CategoryDto> updateCategory(Long id, CategoryDto dto) {
        Optional<Category> optionalEntity = categoryRepository.findByIdAndDeletedAtIsNull(id);
        if (optionalEntity.isPresent()) {
            Category entity = optionalEntity.get();
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setUpdatedAt(LocalDateTime.now());
            return Optional.of(categoryMapper.entityToDto(categoryRepository.save(entity)));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCategory(Long id) {
        Optional<Category> optionalEntity = categoryRepository.findByIdAndDeletedAtIsNull(id);
        if (optionalEntity.isPresent()) {
            Category entity = optionalEntity.get();
            entity.setDeletedAt(LocalDateTime.now());
            categoryRepository.save(entity);
            return true;
        }
        return false;
    }

}
