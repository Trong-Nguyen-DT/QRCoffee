package com.example.admin.service;

import com.example.admin.domain.Order;
import com.example.admin.domain.Table;

import java.util.List;

public interface TableService {

    List<Table> getAllTables();

    List<Table> getAllTableFalse();

    List<Table> getAllTableTrue();

    List<Table> getAllTableYellow(List<Order> orderConfirmedFalse);

    Table getById(Long id);

    void switchTable(Long id, Long switchId);
}
