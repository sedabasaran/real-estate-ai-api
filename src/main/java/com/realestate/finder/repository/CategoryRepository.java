package com.realestate.finder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realestate.finder.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
