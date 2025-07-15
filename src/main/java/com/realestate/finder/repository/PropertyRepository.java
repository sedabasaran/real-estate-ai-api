package com.realestate.finder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.realestate.finder.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {
}

