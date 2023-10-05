package com.example.customer.repository;

import com.example.customer.entity.TableEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {

    @NotNull List<TableEntity> findAll();

    List<TableEntity> findAllByStatusFalse();
}
