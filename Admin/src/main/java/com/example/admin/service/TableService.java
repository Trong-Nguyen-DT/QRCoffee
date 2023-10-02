package com.example.admin.service;

import com.example.admin.domain.Table;

import java.util.List;

public interface TableService {

    List<Table> getAllTables();

    List<Table> getAllTableFalse();
}
