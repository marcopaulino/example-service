package mx.com.marcopaulino.exampleservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.marcopaulino.exampleservice.dto.CategoryDto;
import mx.com.marcopaulino.exampleservice.service.ICategoryService;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> findCategory(@PathVariable Long id) {
        Optional<CategoryDto> optionalDto = categoryService.findCategory(id);
        return optionalDto.map(categoryDto -> new ResponseEntity<>(categoryDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto dto) {
        return new ResponseEntity<>(categoryService.saveCategory(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto dto) {
        Optional<CategoryDto> optionalDto = categoryService.updateCategory(id, dto);
        return optionalDto.map(categoryDto -> new ResponseEntity<>(categoryDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryService.deleteCategory(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
