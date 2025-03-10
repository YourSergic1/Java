package com.github.YourSergic1.datasource.repository;

import com.github.YourSergic1.datasource.model.FieldEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

@Scope("singleton")
public interface FieldRepository extends JpaRepository<FieldEntity, Long> {
}

