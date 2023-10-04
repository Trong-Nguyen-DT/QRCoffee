package com.example.customer.service;

import com.example.customer.domain.Table;

import java.util.List;

public interface TableService {

    List<Table> getAllTables();

    List<Table> getAllTableFalse();

    void setTableTrue(Long id);

    Table getTableById(Long tableId);
}
