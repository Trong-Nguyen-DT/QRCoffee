package com.example.customer.service.Impl;

import com.example.customer.converter.TablesConverter;
import com.example.customer.domain.Table;
import com.example.customer.entity.TableEntity;
import com.example.customer.handler.TableEntityNotFoundException;
import com.example.customer.repository.TableRepository;
import com.example.customer.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TableServiceImpl implements TableService {
     @Autowired
     private TableRepository tableRepository;


    @Override
    public List<Table> getAllTables() {
        return tableRepository.findAll().stream().map(TablesConverter::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Table> getAllTableFalse() {
        return tableRepository.findAllByStatusFalse().stream().map(TablesConverter::toModel).collect(Collectors.toList());
    }

    @Override
    public void setTableTrue(Long id) {
        TableEntity tableEntity = tableRepository.findById(id).orElseThrow();
        tableEntity.setStatus(true);
        tableRepository.save(tableEntity);
    }

    @Override
    public Table getTableById(Long tableId) {
        return TablesConverter.toModel(tableRepository.findById(tableId).orElseThrow(() -> new TableEntityNotFoundException("Table not found")));
    }
}
