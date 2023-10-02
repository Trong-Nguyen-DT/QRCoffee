package com.example.admin.repository;

import com.example.admin.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {

    List<TableEntity> findAll();

    List<TableEntity> findAllByStatusFalse();
}
