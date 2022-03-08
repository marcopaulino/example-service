package mx.com.marcopaulino.exampleservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.marcopaulino.exampleservice.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByDeletedAtIsNull();

    Optional<Category> findByIdAndDeletedAtIsNull(Long id);

}
