package com.realestate.finder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import com.realestate.finder.entity.Category;
import com.realestate.finder.entity.CategoryType;
import com.realestate.finder.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CategoryRepositoryTest {

	@Autowired
    private CategoryRepository categoryRepository;

    @Test
    void whenValidCategory_thenSaved() {
        Category category = new Category();
        category.setName(CategoryType.APARTMENT);

        Category saved = categoryRepository.save(category);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo(CategoryType.APARTMENT);
    }

    @Test
    void whenDuplicateCategoryType_thenThrowsException() {
        Category cat1 = new Category();
        cat1.setName(CategoryType.VILLA);
        categoryRepository.save(cat1);
        categoryRepository.flush();

        Category cat2 = new Category();
        cat2.setName(CategoryType.VILLA);

        assertThrows(DataIntegrityViolationException.class, () -> {
            categoryRepository.save(cat2);
            categoryRepository.flush();
        });
    }

    @Test
    void whenFindAll_thenReturnsSavedCategories() {
        Category cat = new Category();
        cat.setName(CategoryType.HOUSE);
        categoryRepository.save(cat);

        assertThat(categoryRepository.findAll()).isNotEmpty();
    }
}
